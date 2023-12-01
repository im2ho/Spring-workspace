package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Orders")
@Getter @Setter
public class Order {
	//데이터베이스에 주문 정보를 저장하기 위한 클래스
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orderId_seq")
	@SequenceGenerator(name="orderId_seq", sequenceName="orderId_seq", allocationSize=1)
	@Column(name="order_id")
	private Long order_id;
	
	@ManyToOne //x=3같은 관계
	@JoinColumn(name="product_id") 
	private Product product;
	
	private int quantity;
	
}

/*

	@JoinColumn(name="조인하려는 컬럼명") 외래키(Foreign Key)
		데이터베이스에 테이블로 존재하는 객체를 작성
	
	private int quantity : 해당 제품의 수량을 포현 
	
	@ManyToOne
		다대일(N:l) 관례를 설정하는 어노테이션
		주문이 하나의 제품에 속하는 경우 product_id 값이 중복으로 들어갈 수 있음을 나타내기 위함
		
 
*/