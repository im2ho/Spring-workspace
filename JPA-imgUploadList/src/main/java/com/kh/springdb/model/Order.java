package com.kh.springdb.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="customer_order") //order를 찾지 못할 경우 대비
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="order_seq")
	@SequenceGenerator(name="order_seq", sequenceName="order_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	//주문 날짜
	private LocalDate orderDate;
	
	@PrePersist
	public void orderDate() {
		this.orderDate = LocalDate.now();
	}
}
