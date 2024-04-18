package com.kuku9.goods.domain.event_product.dto;

import com.kuku9.goods.domain.event_product.entity.EventProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Value
public class EventProductResponse {

	String productName;
	String productDescription;
	int productPrice;
	String brandName;

	public EventProductResponse(EventProduct eventProduct) {
		this.productName = eventProduct.getProduct().getName();
		this.productDescription = eventProduct.getProduct().getDescription();
		this.productPrice = eventProduct.getProduct().getPrice();
		this.brandName = eventProduct.getProduct().getSeller().getBrandName();
	}
}
