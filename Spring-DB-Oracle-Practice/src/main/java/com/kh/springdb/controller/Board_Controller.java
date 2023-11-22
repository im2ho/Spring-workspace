package com.kh.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.springdb.service.Board_Service;

@Controller
public class Board_Controller {

	@Autowired
	private Board_Service board_Service;
	
	@GetMapping("boardList")
	public String display_Board(Model model) {
		model.addAttribute("boards",board_Service.getAllBoards());
		return "boardList";
	}

}
