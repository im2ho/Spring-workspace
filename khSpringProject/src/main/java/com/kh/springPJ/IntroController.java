package com.kh.springPJ;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroController {
	/*
	html jsp view 공간과 db를 연결하는 역할
	파일 경로를 설정할 때 항상 run하는 java 파일 하위 폴더에다가 설정
	만약 하위로 두지 않고 따로 만들어서 사용할 경우에는 폴더를 바라보는 위치를 별도로 지정해줘야 함
	 */
	@GetMapping("/intro")
	public String getIntro() {
		return "intro";
	}
}