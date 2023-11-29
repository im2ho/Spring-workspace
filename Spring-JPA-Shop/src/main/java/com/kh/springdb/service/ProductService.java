package com.kh.springdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.repository.ProductRepository;
import com.kh.springdb.vo.Products;

@Service
public class ProductService {
	//인터페이스를 사용해서 DB와 상호작용하는데 사용
	//레포지토리를 나타내는 필드
	//final 선언돼서 변경 불가 (SQL문을 가지고오는 녀석이기 때문에 변수 변경 불가하도록 최초 선언으로 묶어버림)
	private final ProductRepository productRepository;
	
	@Autowired //생성자 위한 어노테이션 > Spring은 ProductRepository 타입의 bean을 찾아서 주입
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	//모든 상품을 조회하는 메서드 Repository findAll 메서드를 가지고와서 사용
	public List<Products> getAllProducts(){
		return productRepository.findAll(); //findAll(): 내장된 메서드
	}
	
	//상품 1개만 조회하는 메서드
	public Optional<Products> getProductById(Long product_id){
		return productRepository.findById(product_id);
	}
	
	//저장(insert)하는 메서드
		// if 최초 작성 내용 > 그대로 저장 
		// if 기존에 작성한 내용 > 수정해서 저장
	public Products saveProduct(Products product) {
		return productRepository.save(product);
	}
	
	//삭제하는 메서드
	public void deleteProductById(Long product_id) {
		productRepository.deleteById(product_id);
	}
}

/*
	Optional : 제품의 존재 여부를 체크할 수 있게 해주는 객체 (옵션체크)
		> 추후 null값 혹은 존재하지 않는 값을 url에 입력했을 경우 어떻게 처리할 것인지? 처리 가능하다
*/
