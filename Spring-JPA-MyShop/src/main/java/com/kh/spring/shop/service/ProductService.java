package com.kh.spring.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Product;

@Service
public class ProductService {

	//멤버(필드)변수
	@Autowired
	private ProductRepository productRepository;
	
	//상품 전체 조회 서비스
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//상품 상세 조회 by Id
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	
	//상품 정보 수정 서비스
	
	
	//상품 등록(저장) 서비스
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	

}
