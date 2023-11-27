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
	
	//한 명의 회원 정보 DB에 삽입하기
	public void registerUser(UserModel user) {
		userMapper.insertUser(user);
	}
	
	//유저 정보 수정해서 DB에 저장하기
	public void updateUser(UserModel user) {
		userMapper.updateUser(user);
	}
	
	//유저 삭제 정보 DB에 적용
	public void deleteUser(int mno) {
		userMapper.deleteUser(mno);
	}
}
