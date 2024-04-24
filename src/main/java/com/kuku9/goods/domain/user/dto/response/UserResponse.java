package com.kuku9.goods.domain.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kuku9.goods.domain.user.entity.User;
import com.kuku9.goods.domain.user.entity.UserRoleEnum;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

   private String realName;

   private UserRoleEnum role;

   private  String createdAt;



    public static UserResponse from(User findUser) {
        return new UserResponse(findUser.getRealName(), findUser.getRole(),
            findUser.getCreatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }
}
