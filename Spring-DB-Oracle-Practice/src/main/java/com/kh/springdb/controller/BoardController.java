package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.Board;
import com.kh.springdb.service.BoardService;

@Controller
@RequestMapping("/boards") //url을 한 번에 합쳐준다
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/****************** 게시글 조회 ******************/
	
	@GetMapping //("/boards") 생략된 상태 by @RequestMapping
	public String getAllBoards(Model model) {
		//전체 게시물 보는 Controller (일단 model에 값을 전달해주궛어요)
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards); //model.addAttribute(이름, 리스트명)
		
		return "board-list"; //boardList로 이동
	}
	
	@GetMapping("/{board_id}") //마찬가지로 ("/boards")가 내장되어 생략된 상태
	//@GetMapping("/boards/{board_id}")
	public String getBoardById(@PathVariable int board_id, Model model) {
		//board_id를 통해 게시글 상세보기 Controller
		Board board = boardService.getBoardById(board_id);
		model.addAttribute("board", board);
		
		return "board-detail";
	}
	
	/****************** 게시판 글 작성 ******************/

	//1. GetMapping을 사용해서 게시글 작성하는 html로 이동한 후
	
	@GetMapping("/create") //이동할 공간
	//http GET 요청이 /create라는 경로로 들어올 때 호출됩니달라
	public String displayCreateForm(Model model) { //Model 객체를 매개변수로 받아서 templates(view)으로 데이터를 전달 가능
		//new Board로 새로운 Board 객체를 생성해서 모델에 추가하겟습니달라
		model.addAttribute("board",new Board());
		return "board-form"; //board-form.html 템플릿에서 해당 view를 보여주겟습니다
	}
	
	//2. PostMapping을 사용해서 작성해놓은 insert HTML form 가져온다!
	
	@PostMapping("create")
	public String createBoard(@ModelAttribute Board board) {
		boardService.insertBoard(board);
		return "redirect:/boards"; //글이 작성된 후 돌아갈 템플릿 (boards로 돌아갈랭)
	}
	
	/****************** 게시글 수정 ******************/
	
	@PostMapping("/update/{board_id}")
	public String updateForm(@PathVariable int board_id, @ModelAttribute Board board) {
		//URL에서 가져온 board_id 값을 Board 객체에 저장 (수정한 내용을 setBoard_id를 통해 저장)
		board.setBoard_id(board_id);
		boardService.updateBoard(board);
		
		//수정이 완료된 후 게시글 목록 페이지로 돌아가기
		return "redirect:/boards";
	}
	
	@GetMapping("/update/{board_id}")
	public String displayUpdateForm(@PathVariable int board_id, Model model) {
		Board board = boardService.getBoardById(board_id);
		model.addAttribute("board", board);
		return "board-form";
	}
	
	/****************** 게시글 삭제 ******************/
	
	//게시글 삭제 by board_id
	@GetMapping("/delete/{board_id}")
	public String deleteBoard(@PathVariable int board_id) {
		boardService.deleteBoard(board_id);
		return "redirect:/boards";
	}
	
	//게시물 전체 삭제
	@GetMapping("/delete-all-boards")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
}


/*
	Model model
		컨트롤러에서 뷰로 데이터를 전달할 때 사용하는 인터페이스
		컨트롤러에 있는 메서드에서 매개변수로 Model을 선언하면 Get에 추가한 데이터는 자동으로 뷰에 전달됨
		Select에서 DB에서 담겨온 데이터는 자동으로 모델에 담겨져 View(html)파일로 전달됨
		
	@ModelAttribute Board board
	(@ModelAttribute 클래스명 클래스들대신하는변수명)
		폼 데이터나 URL 경로에서 전달된 데이터를 객체에 넣어줄 때 사용
		클라이언트에서 전송한 데이터를 객체로 값을 넣어주고, 컨트롤러에서 사용할 수 있도록 해주는 것
		전달된 데이터는 mapper를 통해 db에 저장됨
*/