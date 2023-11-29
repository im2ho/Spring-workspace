package com.kh.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.board.service.BoardService;
import com.kh.board.vo.Board;

@Controller
@RequestMapping("/boards")
public class BoardController {
	
	//멤버(필드)변수 final로 초기선언해서 변경 방지
	private final BoardService boardService;
	
	//클래스 생성자
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//전체 게시글 조회 메서드
	@GetMapping
	public String getAllBoards(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		
		return "board_list";
	}
	
	//게시글 상세 보기
	@GetMapping("/details/{id}")
	public String getBoardDetails(@PathVariable("id") Long board_id, Model model) {
		Optional<Board> board = boardService.getBoardById(board_id);
		board.ifPresent(value -> model.addAttribute("board",value));
		return "board_detail";
	}
	
	//INSERT를 위한 GetMapping()과 PostMapping()
	@GetMapping("/new")
	public String displayBoardForm(Model model) {
		model.addAttribute("board", new Board());
		return "board_form";
	}
	@PostMapping("/save")
	public String saveBoard(@ModelAttribute Board board) {
							//작성된 model의 속성을 DB에 전달
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	//수정하기
	@GetMapping("/update/{id}")
	public String getUpdateBoard(@PathVariable("id") Long board_id, Model model) {
		Optional<Board> board = boardService.getBoardById(board_id);
		board.ifPresent(value -> model.addAttribute("board",value));
		return "board_form";
	}
	
	//게시글 삭제하기
	@GetMapping("/delete/{id}")
	public String deleteBoard(@PathVariable("id") Long board_id) {
		boardService.deleteBoard(board_id);
		return "redirect:/boards";
	}
	
	//게시글 전체 삭제
	@GetMapping("/delete-all-boards")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
}
