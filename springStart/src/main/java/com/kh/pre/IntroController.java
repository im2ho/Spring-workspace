package com.kh.pre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class IntroController {
	// http://localhost:8088/web/getIntro
	@RequestMapping("/webapp")
	public void getIntro() {
		System.out.println("getIntro로 이동");
	}
}
