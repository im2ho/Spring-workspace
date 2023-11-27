package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.kh.springdb.model.UserModel;

@Mapper
public interface UserMapper {
	
	//전체 조회 메서드
	List<UserModel> getAllUsers();
	
	//아이디로 1개 조회 메서드
	UserModel getUserById(int id);
	
	//user추가 메서드
	void insertUser(UserModel user);
	
	//유저 정보 수정 메서드
	void updateUser(UserModel user);
	
	//유저 삭제 메서드
	void deleteUser(int mno);
}