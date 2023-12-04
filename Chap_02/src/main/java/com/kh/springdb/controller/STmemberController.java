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

import com.kh.springdb.service.STmemberService;
import com.kh.springdb.vo.STmember;

@Controller
@RequestMapping("/members")
public class STmemberController {

	private STmemberService stService;
	
	@Autowired
	public STmemberController(STmemberService stService) {
		this.stService = stService;
	}
	
	//SELECT(전체조회)
	@GetMapping
	public String getAllMember(Model model) {
		model.addAttribute("members", stService.getAllMember());
		return "memberList";
	}
	
	//INSERT(저장)
	@GetMapping("/new")
	public String showMemberForm(Model model) {
		model.addAttribute("member", new STmember());
		return "memberForm";
	}
	@PostMapping("/save")
	public String saveMember(@ModelAttribute STmember stMember) {
		stService.saveMember(stMember);
		return "redirect/:members";
	}
	
	//UPDATE(수정)
	@GetMapping("/update/{memeberId}")
	public String updateMember(@PathVariable("memberId") Long id, Model model) {
		//Optional 이용해서 id값 가져오기
		Optional<STmember> member = stService.getMemberById(id);
		//람다식 사용해서 (값이 존재할 경우)member에 값을 추가하는 코드 작성
		member.ifPresent(value -> model.addAttribute("member",value));
		return "memberForm";
	}
	
	//DELETE(삭제)
	@GetMapping("/delete/{memberId}")
	public String deleteMember(@PathVariable("memberId") Long id) {
		stService.deleteMemberById(id);
		return "redirect:/members";
	}
}
