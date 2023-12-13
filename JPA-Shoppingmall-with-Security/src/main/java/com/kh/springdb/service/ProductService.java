package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	//멤버(필드)변수
	private final ProductRepository productRepository;
	
	public List<Product> allProductView(){
		return productRepository.findAll();
	}
	
	//페이지네이션 add
	public Page<Product> getList(int page){
		int conPage = Math.max(0, page); //두 인자 중 더 큰 값을 선택
		//conPage:페이지 값, 1:페이지 당 보여줄 목록 개수
		Pageable pageable = PageRequest.of(conPage, 1);
		return productRepository.findAll(pageable);
	}
	
	public void saveProduct(Product product, MultipartFile imgFile) throws IllegalStateException, IOException {
		//MultipartFile : form에서 이미지를 가져오기 위해 사용
		
		//이미지 파일 이름 가져오기
		String originName = imgFile.getOriginalFilename();
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		File saveFile = new File(projectPath, originName);
		
		//MultipartFile에 File로 읽어온 이미지 파일을 저장하기 위해 변환
		//MultipartFile imgFile
		imgFile.transferTo(saveFile);
		product.setImgName(projectPath); //가져온 파일 이름 원본 저장
		//product.setImgPath(projectPath); //경로 저장을 DB에 작성해주기
		product.setImgPath("/img/" + originName);
		
		//저장
		productRepository.save(product);
	}
	
	//상품 상세페이지나 수정하기 위해 아이디를 가져와서 상품을 가져오거나 수정할 수 있게 가져오는 메서드
	public Product getProductById(int id) {
		return productRepository.findProductById(id);
	}

	
	//상품 찜(좋아요) 서비스
	public void likeProduct(int productId) {
		
		Product product = productRepository.findById(productId).orElse(null);
		if(product != null) {
			product.setLikes(product.getLikes()+1);
			productRepository.save(product);
		}
	}
}
