package com.kh.springdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.repository.STmemberRepository;
import com.kh.springdb.vo.STmember;

import jakarta.transaction.Transactional;

@Service
public class STmemberService {
	//데이터 전제조회, 저장, 수정, 삭제
	
	//멤버(필드)변수
	private final STmemberRepository stRepository;
	
	//생성자
	@Autowired //bean은 다시 생성하고 옮길때(?) 초기화가 필요하기 때문에 변수 선언 밑에 어노테이션 작성
	public STmemberService(STmemberRepository stRepository) {
		this.stRepository = stRepository;
	}
	
	//1. 데이터 전체 조회
	public List<STmember> getAllMember(){
		return stRepository.findAll();
	}
	
	//1-1 id로 학생 조회
	public Optional<STmember> getMemberById(Long id) {
		return stRepository.findById(id);
	}
	
	//2. 값을 입력해서 저장하는 메서드
	@Transactional
	public STmember saveMember(STmember stmember) {
		return stRepository.save(stmember);
	}
	
	//3. 삭제하는 메서드
	public void deleteMemberById(Long id) {
		stRepository.deleteById(id);
	}
}
