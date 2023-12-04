package com.kh.springdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.service.CostomerService;
import com.kh.springdb.vo.Costomer;

@Controller
@RequestMapping("/costomers")
public class CostomerController {

	private final CostomerService costomerService;
	
	@Autowired
	public CostomerController(CostomerService costomerService) {
		this.costomerService = costomerService;
	}
	
	//INSERT 회원가입
	@GetMapping("/new")
	public String joinMember(Model model) {
		model.addAttribute("costomer", new Costomer());
		return "joinForm";
	}
	@PostMapping("/save")
	public String saveCostomer(@ModelAttribute Costomer costomer) {
		costomerService.saveCostomer(costomer);
		return "redirect:/costomers";
	}
	
	//SELECT 내 정보 보기
	@GetMapping("/details/{id}")
	public String getCostomerDetials(@PathVariable("id") String id, Model model) {
		Optional<Costomer> costomer = costomerService.getCostomerById(id);
		costomer.ifPresent(value -> model.addAttribute("costomer", value));
		return "costomerDetail" ;
	}
	
	//DELETE 회원 탈퇴
	@GetMapping("/delete/{id}")
	public String deleteCostomer(@PathVariable("id") String id) {
		costomerService.deleteMyInfo(id);
		return "redirect:/costomers";
	}
	
}