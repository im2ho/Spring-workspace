package com.kh.springdb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.vo.Item;
import com.kh.springdb.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //@NotNull로 표시된 필드를 사용해서 생성자를 생성
public class ItemController {

	private final ItemService itemService;
	
	//메인페이지 > 전체 상품 목록 조회
	@GetMapping("/itemlist")
	public String mainPage(Model model) {
		List<Item> items = itemService.allItemList();
		model.addAttribute("items", items);
		//view 템플릿인 html과 연결하기 위해 작성되는 페이지..
		return "itemList";
	}
	
	/*//상품 목록 조회 페이지(페이지네이션 기능 구현 완료되면 쓸거임)
	@GetMapping("/item/list")
	public String itemList(Model model, @PageableDefault(size=12) Pageable pageable, @RequestParam(name="keyword", required = false) String keyword) {
		//페이지네이션 처리
		//검색을 하지 않고 페이징 처리를 원할 경우
		//Page<Item> items = itemService.getItemByPage(pageable);
		return "itemList";
	}*/
	
	//상품등록 페이지 (admin만 등록 가능하도록 수정 예정)
	@GetMapping("/new")
	public String addItemForm() {
		return "addItemForm";
	}
	@PostMapping("/save") //상품 등록으로 입력된 값을 DB에 보내기
	public String saveItem(Item item, MultipartFile photoFile) throws IllegalStateException, IOException {
		//MultipartFile을 이용해서 상품을 등록할 때
		//이미지 파일도 같이 등록될 수 있도록 파라미터 생성
		//itemService.addItem(item);
		//이미지 없이
		itemService.addItem(item, photoFile);
		return "redirect:/index";
	}
	
	//상품 상세
	@GetMapping("/view/{id}")
	public String viewItem(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("item", itemService.getItemById(id));
		return "viewItem";
	}
	
	//상품 수정
	
	
	//상품 삭제
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Integer id) {
		itemService.itemDelete(id);
		return "itemList";
	}
}