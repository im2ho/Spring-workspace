package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.UserMapper;
import com.kh.springdb.model.UserModel;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	//전체 회원 조회 결과 가져오기
	public List<UserModel> getAllUsers(){
		return userMapper.getAllUsers();
	}
	
	//id로 조회한 회원 정보 가져오기
	public UserModel getUserById(int id) {
		return userMapper.getUserById(id);
	}
}
