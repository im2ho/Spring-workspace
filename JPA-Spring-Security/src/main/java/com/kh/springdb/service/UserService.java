package com.kh.springdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SecurityUser;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	//멤버(필드)변수
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입을 할 경우 비밀번호를 암호화해서 DB에 저장할 수 있도록 구현
	public SecurityUser create(String username, String email, String password) {
		SecurityUser user = new SecurityUser();
		user.setUsername(username);
		user.setEmail(email);
		//사용자가 비밀번호를 입력한대로 db에 저장하지 X
		//passwordEncoder를 사용해서 입력받은 비밀번호를 암호화 처리해서 저장하자
		user.setPassword(passwordEncoder.encode(password)); 
		this.userRepository.save(user);
		
		return user;
	}
}
