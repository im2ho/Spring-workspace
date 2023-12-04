package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ProductList") //Member 테이블 만들게요~
@Getter @Setter
public class Product {

	//멤버(필드)변수
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productId_seq")
	@SequenceGenerator(name="productId_seq", sequenceName="productId_seq", allocationSize=1)
	@Column(name="product_id")
	private Long id;
	
	private String product_name;
	
	private String category;
	
	private String price;
	
	private String stock_quantity;
	
	private String description;
}
