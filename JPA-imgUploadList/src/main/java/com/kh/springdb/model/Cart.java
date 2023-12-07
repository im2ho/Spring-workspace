package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cart {
	//장바구니의 상태에 대한 클래스
	
	//비회원 아이디값 & 비회원이 주문한 장바구니 리스트
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="carts_seq")
	@SequenceGenerator(name="carts_seq", sequenceName="carts_seq", allocationSize=1)
	private Long Id;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();

	//order 객체 생성으로 인한 추가 mapperd
	//만약, Entity에 설정한 name이 존재한다면 > @JoinColumn(name="customer_order_id")
	@OneToOne(mappedBy = "cart")
	@JoinColumn(name="order_id")
	private Order order;
	
	public int getTotalAmount() {
		return cartItems.stream().mapToInt(item -> 
			item.getCount() * Integer.parseInt(item.getItem().getPrice())).sum();
	}
	
	public int getTotalCount() {
		//람다식
		//stream() : 리스트로 받아서 스트림으로 변환하는 메서드
			//원래는 List나 Map의 반환경우, 배열처리를 해서 총 가격 합을 받아야하지만
			//stream을 이용시, 배열처리 없이 한 번에 값을 받을 수 있도록 처리됨
		//mapToInt() :
			//CartItem의 객체를 int로 감싸주는 메서드
			//CartItem에서 getCount 메서드를 호출해서 각 CartItem에 연결된
			//count 값을 가져오고, 이 값을 int로 감싸준다
		//sum() : 앞에서 int로 감싸진 count 값을 모두 더해주는 메서드
		return cartItems.stream().mapToInt(CartItem::getCount).sum();
	}
	
	//카트에 담긴 총 상품 개수
	private int count;
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cart_seq")
	@SequenceGenerator(name="cart_seq", sequenceName="cart_seq", allocationSize=1)
	private int id;
	
	//카트에 담긴 상품 리스트를 넣기 위한 배열 생성
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems = new ArrayList<>();
	
	
	
	//카트에 담긴 총 상품 개수
	private int count;
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private LocalDate createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	
	public static Cart createCart() {
		Cart cart = new Cart();
		cart.setCount(0); //장바구니 처음 상태 : 담긴 상품 0
		return cart;
	}
	*/
	
}
