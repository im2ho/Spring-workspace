package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Item;
import com.kh.springdb.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	//모든 상품 리스트 불러오기
	public List<Item> allItemView(){
		return itemRepository.findAll();
	}
	
	//아이템 상세보기, 수정하기
	public Item getItemById(int id) {
		return itemRepository.findItemById(id);
	}
	
	//상품 등록 메서드
	//기존 : 상품명이나 글자로 이루어진 내용
	//이미지 추가 : 파일을 파라미터에 받아야함
	//JPA-ImgUpload-Cart-Order의 ItemService 메서드와 코드를 비교해보세용
	public void saveItem(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		
		/*

			MultiPartFile : 
				인터페이스 > 클라이언트로부터 전송된 파일 데이터를 처리하기 위한 메서드를 정의 (파일 형식이 정해지지 않음)
				form multipart : form-data 로 전송된 파일을 처리
			
			File :
				Java에서 제공하는 클래스
				로컬(개발자) 저장소에서 파일을 저장하거나 수정할 때 사용
				
			transferTo :
				MultipartFile 인터페이스 내에 존재하는 메서드
				업로드된 파일을 지정된 파일경로로 저장하기 위해서 사용

		*/
		
		//UUID : 고유 식별값
		UUID uuid = UUID.randomUUID();
		
		String originName = imgFile.getOriginalFilename();
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		
		File saveFile = new File(projectPath, uuid + originName);
		
		imgFile.transferTo(saveFile);
		
		item.setImgName(projectPath);
		
		//item.setImgPath(projectPath);
		
		item.setImgPath("/img/" + uuid + originName);
		
		itemRepository.save(item);
		
	}
}
