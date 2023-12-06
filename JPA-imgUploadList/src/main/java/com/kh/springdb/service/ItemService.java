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
	
	//상세 보기
	public Optional<Item> ItemDetailView(int id) {
		return itemRepository.findById(id);
	}
	
	//상품 등록 메서드
	//기존 : 상품명이나 글자로 이루어진 내용
	//이미지 추가 : 파일을 파라미터에 받아야함
	public void saveItem(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		
		String originName = imgFile.getOriginalFilename();
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		
		UUID uuid = UUID.randomUUID();
		
		File saveFile = new File(projectPath, originName);
		
		imgFile.transferTo(saveFile);
		
		item.setImgName(projectPath);
		
		//item.setImgPath(projectPath);
		
		item.setImgPath("/img/" + uuid + originName);
		
		itemRepository.save(item);
	}
}
