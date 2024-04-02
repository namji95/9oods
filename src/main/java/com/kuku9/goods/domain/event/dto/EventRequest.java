package com.kuku9.goods.domain.event.dto;

import com.kuku9.goods.domain.event_product.dto.EventProductRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
public class EventRequest {

    @NotNull(message = "이벤트를 등록하시려면 제목을 입력하세요.")
    private String title;

    @NotNull(message = "이벤트를 등록하시려면 내용을 입력하세요.")
    private String content;

    private Long limitNum;

    @NotNull(message = "이벤트를 등록하시려면 오픈일자를 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openAt;

    private List<EventProductRequest> eventProducts;

}
