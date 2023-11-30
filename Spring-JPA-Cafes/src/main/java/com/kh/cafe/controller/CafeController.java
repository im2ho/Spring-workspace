package com.kh.cafe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafe.service.CafeService;
import com.kh.cafe.vo.Cafe;

@Controller
@RequestMapping("/cafes")
public class CafeController {
	
	//멤버(필드)변수
	private final CafeService cafeService;
	
	//생성자
	@Autowired
	public CafeController(CafeService cafeService) {
		this.cafeService = cafeService;
	}
	
	//전체 카페 조회 메서드
	@GetMapping
	public String getAllCafes(Model model, @RequestParam(required=false)String name) {
		
		/*
			@RequestParam(required=false) :  
				파라미터를 필수로 적어주지 않아도 됨을 나타냄
				
			@RequestParam :
				http요청으로 파라미터를 메서드의 매개변수로 전달할 때,
				클라이언트가 웹 애플리케이션에 보내는 요청의 파라미터 값을받아서 처리
			
			==============================	
				
			@PathVariable & @RequestParam 
			
				@PathVariable : 
					URL 경로에서 변수 값을 추출 (url/cafes/{id})
				
				@RequestParam : 
					한 경로 안에서 클라이언트가 요청한 파라미터 값을 추출 (url/cafes?name=사용자가 폼에 입력한 값)
			
		*/
		
		List<Cafe> cafes;
		
		if(name != null && !name.isEmpty()) {
			cafes = cafeService.findCafeByJPA(name);
		} else {
			//검색 내용이 없으면 그냥 전체 목록 띄우기
			cafes = cafeService.getAllCafes();
		}
		
		model.addAttribute("cafes",cafes);
		return "cafe_list";
	}
	
	//특정 카페 세부정보 조회
	@GetMapping("/details/{id}")
	public String getCafeById(@PathVariable("id") Long cafe_id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(cafe_id);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafe_detail";
	}
	
	//카페 정보 추가
	@GetMapping("/new")
	public String addCafe(Model model) {
		model.addAttribute("cafe", new Cafe());
		
		return "cafe_form";
	}
	
	@PostMapping("/save")
	public String saveCafe(@ModelAttribute Cafe cafe) {
		cafeService.saveCafe(cafe);
		
		return "redirect:/cafes";
	}
	
	//수정도 가능해용
	@GetMapping("/update/{id}")
	public String updateCafe(@PathVariable("id") Long cafe_id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(cafe_id);
		cafe.ifPresent(value -> model.addAttribute("cafe",value));
		return "cafe_form";
	}
	
	//특정 카페 정보 삭제
	@GetMapping("/delete/{id}")
	public String deleteCafe(@PathVariable("id") Long cafe_id) {
		cafeService.deleteCafe(cafe_id);
		return "redirect:/cafes";
	}
	
	//카페 전체 삭제
	@GetMapping("/delete-all-cafes")
	public String deleteAllCafes() {
		cafeService.deleteAllCafes();
		return "redirect:/cafes";
	}
	
	
	//특정 키워드를 활용해서 상호명을 검색하는 메서드
	@GetMapping("/search")
	public String serachCafesByJPA(@RequestParam String keyword, Model model) {
			
		List<Cafe> cafes = cafeService.findCafeByName(keyword);
			
		model.addAttribute("cafes",cafes);
			
		return "searchResult";
	}
	
}
