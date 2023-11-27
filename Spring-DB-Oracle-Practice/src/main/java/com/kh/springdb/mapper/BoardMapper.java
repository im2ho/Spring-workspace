package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.Board;

//인터페이스
@Mapper
public interface BoardMapper {
	
	//모든 게시물 조회
	List<Board> getAllBoards();
	
	//게시글 1개 조회 by board_id
	Board getBoardById(int board_id);
	
	//게시물 작성(저장)
	//void insertBoard(Board board);
	void insertBoard(Board board);
	
	//게시물 수정
	void updateBoard(Board board);
	
	//board_id에 해앋하는 게시글 삭제
	void deleteBoard(int board_id);
	
	//게시글 전체 삭제
	void deleteAllBoards();
}
