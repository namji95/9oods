package com.kuku9.goods.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuku9.goods.domain.auth.config.KakaoProperties;
import com.kuku9.goods.domain.auth.dto.KakaoUserInfo;
import com.kuku9.goods.domain.user.entity.User;
import com.kuku9.goods.domain.user.repository.UserRepository;
import com.kuku9.goods.global.security.jwt.JwtUtil;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final KakaoProperties kakaoProperties;


    @Transactional
    public String login(String code) throws JsonProcessingException {
        String kakaoAccessToken = getKakaoAccessToken(code);
        KakaoUserInfo kakaoUserInfo = getkakaoUserInfo(kakaoAccessToken);
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);
        return jwtUtil.createAccessToken(kakaoUser.getEmail(), kakaoUser.getRole());
    }


    public String getKakaoAccessToken(String code) throws JsonProcessingException {
        URI uri = UriComponentsBuilder.fromUriString(kakaoProperties.getAuthUrl() + "/token").encode().build()
            .toUri();

        // Http Response Body 객체 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoProperties.getClientId());
        body.add("redirect_uri", kakaoProperties.getRedirectUrl());
        body.add("code", code);
        body.add("client_secret", kakaoProperties.getClientSecret());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        RequestEntity<MultiValueMap<String, String>> kakaoTokenRequest = RequestEntity.post(uri)
            .headers(headers).body(body);


        ResponseEntity<String> response = restTemplate.exchange(kakaoTokenRequest, String.class);

        JsonNode jsonNode = new ObjectMapper().readTree(
            response.getBody());
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfo getkakaoUserInfo(String kakaoAccessToken) throws JsonProcessingException {

        URI uri = UriComponentsBuilder.fromUriString(kakaoProperties.getApiUrl()).path("/v2/user/me").encode()
            .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity.post(uri)
            .headers(headers).body(new LinkedMultiValueMap<>());

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long kakaoId = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();
        return KakaoUserInfo.from(kakaoId,nickname, email);

    }


    private User registerKakaoUserIfNeeded(KakaoUserInfo kakaoUserInfo) {
        Long kakaoId = kakaoUserInfo.getKakaoId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            String kakaoEmail = kakaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kakaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;

                kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
            } else {
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);

                String email = kakaoUserInfo.getEmail();
                kakaoUser = User.fromKakao(email, encodedPassword,kakaoUserInfo.getNickname(), kakaoId);
            }
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }


}
