package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.Board_Mapper;
import com.kh.springdb.model.Board_Model;

@Service
public class Board_Service {
	
	@Autowired
	private Board_Mapper board_Mapper;
	
	public List<Board_Model> getAllBoards(){
		return board_Mapper.getAllBoards();
	}
}
