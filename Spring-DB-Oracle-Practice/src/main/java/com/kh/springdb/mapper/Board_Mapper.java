package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.Board_Model;

//인터페이스
@Mapper
public interface Board_Mapper {
	
	List<Board_Model> getAllBoards();
}
