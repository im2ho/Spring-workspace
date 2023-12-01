package com.kh.spring.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//상품번호를 이용해서 찾기
	Optional<Product> findById(Long product_id);
}
