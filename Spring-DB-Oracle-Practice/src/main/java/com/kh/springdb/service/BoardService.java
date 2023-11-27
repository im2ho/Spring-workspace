package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.springdb.mapper.BoardMapper;
import com.kh.springdb.model.Board;

@Service
public class BoardService {
	
	@Autowired //bean 불러오기
	private BoardMapper boardMapper;
	
	//전체 게시글 조회
	public List<Board> getAllBoards(){
		return boardMapper.getAllBoards();
	}
	
	//전체 게시판에서 게시물 1개 선택 > 상세보기 서비스 by board_id
	public Board getBoardById(int board_id) {
		return boardMapper.getBoardById(board_id);
	}
	
	//게시판에서 게시글 작성하기
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}
	
	//게시판에서 게시글 수정하기
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}
	
	//게시판에서 게시글 삭제 by board_id
	public void deleteBoard(int board_id) {
		boardMapper.deleteBoard(board_id);
	}
	
	//게시글 전체 삭제
	@Transactional
	public void deleteAllBoards() {
		boardMapper.deleteAllBoards();
	}
	
}

/*

	@Transactional
		트랜잭션을 지원하는 스프링에서
		데이터베이스 관리를 단순히 어노테이션을 사용해서
		여러개의 데이터베이스 조작 작업을 묶어서 하나의 작업 단위로 처리하는데 사용하며
		작업은 성공 또는 실패로 완료 가능
		
		> 개발자가 일일이 커밋 혹은 롤백을 관리하는 코드를 작성할 필요 X
 */