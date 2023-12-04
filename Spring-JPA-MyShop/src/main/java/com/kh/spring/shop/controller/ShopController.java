package com.kh.spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.spring.shop.service.ShopService;
import com.kh.spring.shop.vo.Product;

//월요일에 CartController 생성 예정
//주문 , 결제 only
@Controller
public class ShopController {
	
	//멤버(필드)변수
	@Autowired
	private ShopService shopService;
	
	//생성자
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}
	
	//주문에 관련된 내용을 처리하는 메서드 > 주문을 처리하고 주문한 결과를 Order에 반환
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Product product, int quantity, Model model) {
		
		shopService.placeOrder(product, quantity);
		
		//model에 필요한 데이터 추가
		model.addAttribute("msg","주문이 성공적으로 전달되었습니다.");
		
		//주문확인 페이지로 이동
		return "redirect:/orderCheck";
	}
	
	@PostMapping("/paymentFinish")
	public String processPayment(Long orderId, String paymentStatus, Model model) {
		//주문에 대한 결제를 처리하고 payment를 반환
		shopService.savePayment(orderId, paymentStatus);
		model.addAttribute("msg", "결제가 성공적으로 처리되었습니다.");
		
		return "redirect:/paymentCheck";
	}
}

/*

	@RequestBody
		정보를 URL이 아닌, 자바 객체로 받음
		요청했던 body에 위치하도록 할 때 사용
	
	@RequestParam
		정보를 URL에 저장

*/