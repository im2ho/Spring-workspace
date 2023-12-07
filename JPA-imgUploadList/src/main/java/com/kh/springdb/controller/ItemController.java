package com.kh.springdb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Item;
import com.kh.springdb.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	//메인페이지
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("items", items);
		return "index";
	}
	
	//상품 전체 목록 페이지
	@GetMapping("/item/list")
	public String itemList(Model model) {
		List<Item> items = itemService.allItemView();
		model.addAttribute("items",items);
		return "itemList";
	}
	
	//상세 페이지
	@GetMapping("/item/detail/{id}")
	public String itemDetail(@PathVariable("id") int id, Model model) {
		Item item = itemService.getItemById(id);
		model.addAttribute("item",item);
		return "itemDetail";
	}
	
	//상품 등록 페이지로 이동
	@GetMapping("/item/new")
	public String itemSaveForm(Model model) {
		return "addItemForm";
	}
	
	//클라이언트의 상품 등록 내용을 DB에 업로드
	@PostMapping("/item/new")
	public String itemSave(Item item, MultipartFile imgFile) throws IllegalStateException, IOException {
		itemService.saveItem(item, imgFile);
		return "redirect:/item/list";
	}
}
