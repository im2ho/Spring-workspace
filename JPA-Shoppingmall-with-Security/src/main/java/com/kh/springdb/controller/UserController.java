package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCheckForm;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	//회원가입 창
	@GetMapping("/signup")
	public String signUp(UserCheckForm userCheckForm) {
		return "signup_form";
	}
	//회원가입 > 값이 존재하는가? 원하는 조건에 부합하는가? 확인 후 전달
	@PostMapping("/signup")
	public String signUp(@Valid UserCheckForm usercheckform, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!usercheckform.getPassword1().equals(usercheckform.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncCorrect", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return "signup_form";
		}
		
		//UserRole role = usercheckform.getIsRole();
		
		userService.create(usercheckform.getUsername(), usercheckform.getEmail(), usercheckform.getPassword1(), usercheckform.getIsRole());
		return "redirect:/";
	}
	
	//로그인
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
