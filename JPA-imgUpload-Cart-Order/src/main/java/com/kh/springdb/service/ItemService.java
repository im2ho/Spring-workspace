package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
//import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.vo.Item;
import com.kh.springdb.repository.ItemRepository;

@Service
public class ItemService {
	
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	//상품 추가
	public void addItem(Item item, MultipartFile photoFile) throws IllegalStateException, IOException {
		
		//상품명과 저장될 파일명, 경로 생성
		//이미지 파일 정보에 대해서 추출
		String originPhotoName = photoFile.getOriginalFilename();
		String photoName = ""; //빈(empty)값 설정
		//업로드된 이미지 파일의 원본 파일명을 가져옴
		String photoPath= System.getProperty("user.dir") + "src/main/resources/static/uploadImg/"; //업로드 된 이미지 파일 경로 설정
			//System.getProperty() : 현재 코드가 작업하고있는 폴더 위치
			//"user.dir" : 현재 작업하고있는 사용자 폴더
			//C:/Users/user1/springBoot-workspace/JPA-ImgUpload-Cart-Order와 동일
			
		//새로운 파일명을 생성해주기 위해 UUID
		//UUID uuid = UUID.randomUUID();
		
		String saveFileName = "khShop_" + originPhotoName;
		//saveFileName으로 사진1을 올리면, 폴더 내에는 khShop_사진1으로 저장이 됨
		
		photoName = saveFileName;
		
		//파일을 저장하기 위해 우리가 작성해놓은 파일을 작성하기 위한 공간이 비어있는 File 객체를 가지고 옴
								//파일을 저장할 경로, 파일명
		File saveFile = new File(photoPath, photoName);
		
		/*
		 * 만약 이름을 변경해서 저장하고싶지 않다면 originPhotoName으로 저장해도 됨
		 * File saveFile = new File(photoPath, photoName); 
		 * 	> 판매자 컴퓨터에 있는 이미지 이름으로 그대로 업로드 된다
		 */
		
		//업로드 된 이미지 파일을 지정된 경로에 저장하기 위해 설정
		//transferTo() : 서버에 특정 경로에 저장할 수 있도록 해주는 메서드 (컴퓨터에 저장해주궛어요~)
		photoFile.transferTo(saveFile);
		item.setImgName(photoName);
		item.setImgPath("/img/" + photoName);
		
		//DB에 저장할 수 있도록 save
		itemRepository.save(item);
	}

	//개별 상품 읽기
	public Item getItemById(Integer id) {
		return itemRepository.findById(id).get();
	}
	
	//findById(id): id에 해당하는 값을 가져오고
	//get() : Item 객체를 반환
	
	//모든 상품 가지고오기(List)
	public List<Item> allItemList(){
		return itemRepository.findAll();
	}
	
	//상품 삭제
	public void itemDelete(Integer Id) {
		itemRepository.deleteById(Id);
	}
}
