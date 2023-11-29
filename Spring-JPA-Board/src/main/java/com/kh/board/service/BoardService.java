package com.kh.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.board.repository.BoardRepository;
import com.kh.board.vo.Board;

@Service
public class BoardService {
	
	//Repository를 나타내는 필드
	//Repository는 SQL문을 가져오기 때문에 변수 변경 불가하도록 final로 최초 선언
	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	//전체 상품 조회 메서드
	public List<Board> getAllBoards(){
		return boardRepository.findAll();
	}
	
	//상세정보 조회 메서드
	public Optional<Board> getBoardById(Long board_id){
		return boardRepository.findById(board_id);
	}
	
	//게시물 작성(추가)하기
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
	
	//게시물 삭제하기 by Id
	public void deleteBoard(Long board_id) {
		boardRepository.deleteById(board_id);
	}
	
	//게시물 전체삭제 by Id
	public void deleteAllBoards() {
		boardRepository.deleteAll();
	}
}
