package com.kh.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.shop.vo.Member;

//회원가입, 로그인, 마이페이지, 아이디 찾기
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//이메일을 이용해서 아이디를 찾기
	Member findByEmail(String email);
	
	
}