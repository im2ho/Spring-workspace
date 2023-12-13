package com.kh.springdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SecurityUser;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 할 경우 비밀번호를 암호화해서 DB에 저장
	public SecurityUser create(String username, String email, String password, String role) {
		SecurityUser user = new SecurityUser();
		user.setUsername(username);
		user.setEmail(email);
		
		user.setPassword(passwordEncoder.encode(password));
		//사용자 역할 설정
		user.setIsRole(role);
		
		//방법2: String값(isRole)로 설정할 때
		//user.setIsRole(role);
		userRepository.save(user);
		
		return user;
	}
}
