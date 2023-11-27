package com.kh.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.LoginMapper;
import com.kh.springdb.model.UserModel;

@Service
//LoginService : 스프링에서 사용자한테 필요한 메서드를 제공하는 서비스 역할
public class LoginService {
	
	// DB가 담긴 loginMapper를 사용하기 위해 선언
	private LoginMapper loginMapper;
	
	@Autowired //의존성 주입(DI : 인터페이스를 주입받는 생성자임을 정의)
	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}
	
	//로그인 기능을 수행하는 login 메서드를 정의
	public UserModel login(String mname, String memail) {
		//loginMapper 안에 있는 loginByMnameAndMemail 메서드 호출,
		//mname,memail 사용해서 db에서 해당 정보를 조회하겟음..
		//추후 public User를 통해 조회된 결과인 User에 담긴 객체를 반환
		return loginMapper.loginByMnameAndMemail(mname, memail);
	}
}
