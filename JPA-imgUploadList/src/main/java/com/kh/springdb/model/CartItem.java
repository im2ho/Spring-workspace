package com.kh.springdb.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class CartItem {

	//장바구니에 담긴 '상품'에 대한 클래스
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cartItems_seq")
	@SequenceGenerator(name="cartItems_seq", sequenceName="cartItems_seq", allocationSize=1)
	private int id;
	
	//장바구니에 담는 행위를 위해 'Cart' 클래스 가져오기
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	//장바구니에 담긴 상품이 어떤 상품인지 알기위해, 'Item' 클래스 가져오기
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="item_id")
	private Item item;
	
	//상품의 개수
	private int count;
	
	//생성자=================================================================
	
	public static CartItem createCartItem(Cart cart, Item item, int amount) {
		
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setCount(amount); //count에 입력한 amount가 들어간다
		return cartItem;
		
	}
	
	//이미 담겨있는 물건이나 또는 담을 물건이 존재할 경우
	public void addCount(int count) {
		this.count += count;
	}
	
}

/*

	< Cart와 CartItem의 차이 >

	Cart.java
		사용자가 담은 상품들을 모아두는 곳
		User.java와 연결시, User - Cart 의 연관이 생성될 예정
	
	CartItem.java
		장바구니에 담긴 각(개별) 상품의 정보를 나타냄


*/