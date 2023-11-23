package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kh.springdb.model.UserModel;
import com.kh.springdb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//전체 아이디 가지고 오기 위해서 GetMapping 사용
	@GetMapping("/users-info")
	public String getAllUsers(Model model){
		List<UserModel> users = userService.getAllUsers();
		model.addAttribute("users",users);
		
		return "users-info"; //return 템플릿명
	}
	
	@GetMapping("/user-info/{id}")
	public String getUserById(@PathVariable int id, Model model) {
		UserModel user = userService.getUserById(id);
		model.addAttribute("user", user);
		
		return "user-info";
	}
	
} //class UserController

/*
	@PathVariable : 경로에 대한 변수를 메서드의 매개변수와 

*/