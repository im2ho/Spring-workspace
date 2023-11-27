package com.kh.springdb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.UserModel;

@Mapper
//interface LoginMapper : 데이터베이스와 관련된 로그인 작업에 대한 메서드를 정리할 예정
public interface LoginMapper {
	
	//loginByMnameAndMemail : 
	//로그인을 할 경우 사용되는 사용자 이름과 이메일을 입력 받아 DB에서 해당 정보를 조회하고
	//그 결과를 UserModel 객체를 통해 반환할 것으로 지정해 줌
	
						//로그인을 하기 위해 필요한 요소들
	UserModel loginByMnameAndMemail(String mname, String memail);
}
