package com.kh.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.MemberRepository;
import com.kh.spring.shop.vo.Member;

@Service
public class MemberService /*implements UserDetailsService*/ {

	//멤버(필드)변수
	@Autowired
	private MemberRepository memberRepository;
	
	/*생성자
	@Autowired
	private MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}*/
	
	//1. 회원가입 메서드
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}
	
	//1.-1 회원가입 중복체크 (이메일로 체크할게용)
	public void validateDuplicateMember(Member member) {
		
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		//이미 존재하는 회원일 경우 > Exception 활용하기
		//DB에서 이미 가입된 회원의 이메일이 존재한다면 예외를 발생시키는 if문
		if(findMember != null) {
			//IllegalStateException : 어떤 행위를 요청했을 때, 그것이 불가능할 경우
			throw new IllegalStateException("이미 존재하는 회원정보입니다");
		}
	}
}

/*
	UserDetailsService
		사용자 정보를 가지고 오거나 인증할 때 사용하는 서비스
		사용자의 아이디나 이메일을 받아와서 받아온 정보를 객체로 반환할 때 사용
*/