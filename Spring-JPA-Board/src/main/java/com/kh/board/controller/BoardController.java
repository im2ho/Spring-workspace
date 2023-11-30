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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//특정 키워드를 활용해서 게시물을 검색하는 Mapping 메서드
	@GetMapping("/search")
	public String searchBoards(@RequestParam String keyword, Model model) {
		//특정 키워드를 포함해서 게시물 검색할 수 있도록 설정
		List<Board> boards = boardService.findBoardByTitle(keyword);
		
		//모델에 검색 결과 추가
		model.addAttribute("boards", boards);
		
		//검색 결과를 보여줄 페이지 리턴
		return "searchResult";
	}
}

/*

	@RequestParam :
		Spring 프레임워크에서 클라이언트로부터 전송된 HTTP 요청의 파라미터 값을 받아오기 위해 사용되는 어노테이션
		주로 웹 요청에서 쿼리 파라미터나 폼 데이터를 추출하는데 사용
		클라이언트가 전송한 요청의 파라미터 값을 메서드의 매개변수로 받아올 때 사용한다.
		
		예시)
			@GetMapping("/예시url")
			public String 어쩌구메서드(@RequestParam String name, @RequestParam int age){
				//name, age : 클라이언트가 전송한 요청의 쿼리 파라미터 값
				
				return "View";
			}

*/