package com.kuku9.goods.domain.seller.service;

import com.kuku9.goods.domain.seller.dto.ProductRegistRequestDto;
import com.kuku9.goods.domain.seller.dto.ProductUpdateRequestDto;
import com.kuku9.goods.domain.seller.dto.SellProductStatisticsResponseDto;
import com.kuku9.goods.domain.seller.dto.SellingProductResponseDto;
import com.kuku9.goods.domain.seller.entity.Seller;
import com.kuku9.goods.domain.user.entity.User;
import com.kuku9.goods.global.security.CustomUserDetails;
import java.util.List;

public interface SellerService {

    Long createProduct(ProductRegistRequestDto requestDto, User user);

    Long orderProductStatus(Long productsId, User user);

    Long updateProduct(
        Long productId, ProductUpdateRequestDto requestDto, User user);

    List<SellingProductResponseDto> getSellingProduct(User user);

    SellProductStatisticsResponseDto getSellProductStatistics(User user);

    /**
     *  셀러 db 저장
     * @param seller
     * @return null
     */
    Void save(Seller seller);

    /**
     *  유저가 셀러등록이 존재하는지 체크
     * @param userId 유저아이디
     * @return true, false
     */
    Boolean checkSellerExistsByUserId(Long userId);

    /**
     *  브랜드 이름이 존재하는지 검사
     * @param brandName 브랜드 이름
     * @return true, false
     */
     Boolean isBrandNameUnique(String brandName);

    /**
     *  도메인 이름이 존재하는지 검사
     * @param domainName 도메인 이름
     * @return true, false
     */
     Boolean isDomainNameUnique(String domainName);

    /**
     *  셀러 이메일이 존재하는지 검사
     * @param email 셀러 이메일
     * @return true, false
     */
    Boolean isEmailUnique(String email);

    /**
     *  셀러 전화번호가 이미 등록되어 있는지 검사
     * @param phoneNumber 셀러 전화번호
     * @return true, false
     */
    Boolean isPhoneNumberUnique(String phoneNumber);

}
