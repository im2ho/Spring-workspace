package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCheckForm;
import com.kh.springdb.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	//멤버(필드)변수
	private final UserService userService;
	
	//회원가입할 때 들어갈 창 조회
	@GetMapping("/signUp")
	public String signUp(UserCheckForm userCheckForm) {
		return "signup_form";
	}
	
	//회원가입 진행시 값이 존재하는지 원하는 조건에 맞는지 확인 후 전달
	@PostMapping("/signUp")
	public String signUp(@Valid UserCheckForm usercheckform, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
            return "signup_form";
        }
		
		//1. 비밀번호와 비밀번호 확인이 일치하지 않을 경우
		//bindingResult.rejectValue(a,b,c)
			// a : 검증에 실패한 필드의 이름 > password2가 검증에 실패
			// b : 검증에 실패할 경우 보일 메세지를 담은 변수 > passwordCorrect라는 이름으로 실패 메세지 저장
			// c : (b가 담고있는) 비밀번호가 일치하지 않는 에러가 났을 경우 보일 메세지
		if(!usercheckform.getPassword1().equals(usercheckform.getPassword2())) {
			bindingResult.rejectValue("password2","passwordInCorrect","비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "signup_form";
		}
		//모든 내용을 잘 작성했을 경우, DB에 저장해준다
		userService.create(usercheckform.getUsername(), usercheckform.getEmail(), usercheckform.getPassword1());
		
		//회원가입이 완료됐을 경우, 메인페이지로 이동
		return "redirect:/";
	}
	
	//로그인
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

}
