package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Payments")
@Getter @Setter
public class Payment {
	//데이터베이스에 주문 정보를 저장하기 위한 클래스
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="paymentId_seq")
	@SequenceGenerator(name="paymentId_seq", sequenceName="paymentId_seq", allocationSize=1)
	//@Column(name="payment_id")
	private Long payment_id;
	
	@ManyToOne
	@JoinColumn(name="order_id") 
	private Order order;
	
	private String paymentStatus;
	
}

/*

	@JoinColumn(name="조인하려는 컬럼명") 외래키(Foreign Key)
		데이터베이스에 테이블로 존재하는 객체를 작성
	
	private int quantity : 해당 제품의 수량을 포현 
	
	@ManyToOne
		다대일(N:l) 관례를 설정하는 어노테이션
		주문이 하나의 제품에 속하는 경우 product_id 값이 중복으로 들어갈 수 있음을 나타내기 위해
		
 
*/