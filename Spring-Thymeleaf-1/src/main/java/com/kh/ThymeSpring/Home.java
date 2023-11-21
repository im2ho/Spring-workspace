package com.kh.ThymeSpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class Home {
	@GetMapping("/home")
	public String homehomehome(Model model) {
		model.addAttribute("message","Let me go home");
		return "home";
	}
}
