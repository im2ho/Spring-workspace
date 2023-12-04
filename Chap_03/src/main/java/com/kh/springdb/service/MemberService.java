package com.kh.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.dao.MemberDAO;
import com.kh.springdb.model.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	//전체보기
	
	/*멤버 정보 상세보기
	public MemberVO getMemberById(int memberId) {
		return memberDAO.getMemberById(memberId);
	}*/
	
	//INSERT : 멤버 정보 저장 및 수정
	public void insertMember(MemberVO member) {
		memberDAO.insertMember(member);
	}
	
	//DELETE : 삭제하기
}
