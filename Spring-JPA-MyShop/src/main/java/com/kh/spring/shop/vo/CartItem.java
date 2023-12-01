package com.kh.spring.shop.vo;

import lombok.*;

@Getter
@Setter
//장바구니 내 상품
public class CartItem {

	//멤버(필드)변수
	private Product product;
	private int quantity;
	
	//생성자
	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
}
