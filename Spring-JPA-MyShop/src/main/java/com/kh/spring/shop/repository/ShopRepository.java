package com.kh.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Order;

public interface ShopRepository extends JpaRepository<Order, Long> {
	
	//주문번호 생성 쿼리 메서드
	//public void saveOrder();
	
	//상품상제 정보
	//public void getProductById();
}