package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.ProductMapper;
import com.kh.springdb.model.ProductModel;

@Service
public class ProductService {
	/*
		JPA 방식 (우린 mybatis 쓸거긴한데 방법만 알고갑시달라)
		@Autowired //의존관계 주입 어노테이션
		private ProductRepository productRepository;
	*/
	
	@Autowired
	private ProductMapper productMapper; //mapper파일 import
	
	public List<ProductModel> getAllProducts(){
		//return productRepository.findAll(); //이건 jpa방식..아마도
		return productMapper.getAllProducts(); //조회한 제품 모두 다 가져오는 메서드를 반환
	}
	
}
