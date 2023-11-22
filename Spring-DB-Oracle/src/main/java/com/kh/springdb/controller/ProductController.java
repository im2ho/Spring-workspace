package com.kh.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.springdb.service.ProductService;
//RequestMapping vs GetMapping

@Controller //컨트롤러: model을 html(view)에 전달만 해줄 것. 기능적인 부분은 service에 넘기기~
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("productList") //주소 지정
	public String displayProduct(Model model) {
		//model에 전체 상품의 리스트를 추가하겠어요~
		//				("변수명",내용이 저장됭어있는 변수명)
		model.addAttribute("products",productService.getAllProducts());
		
		return "productList"; //ThymeLeaf가 저장되어있는 템플릿의 이름
	}
}
