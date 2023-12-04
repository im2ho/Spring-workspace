package com.kh.spring.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	//멤버(필드)변수
	private final ProductRepository productRepository;
	
	@Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	//상품 전체 조회 서비스
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//상품 상세 조회 by Id
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	
	//상품 정보 수정(저장) 서비스
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
	
	//상품 삭제 서비스
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	

}
