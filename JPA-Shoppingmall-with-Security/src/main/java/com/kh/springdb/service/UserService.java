package com.kh.springdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SecurityUser;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 할 경우 비밀번호를 암호화해서 DB에 저장
	public SecurityUser create(String usernaem, String email, String password) {
		SecurityUser user = new SecurityUser();
		user.setUsername(usernaem);
		user.setEmail(email);
		
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		
		return user;
	}
}
