package com.kh.springdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.repository.CostomerRepository;
import com.kh.springdb.vo.Costomer;

@Service
public class CostomerService {

	//멤버(필드)변수
	private CostomerRepository costomerRepository;
	
	//생성자
	@Autowired
	public CostomerService(CostomerRepository costomerRepository) {
		this.costomerRepository = costomerRepository;
	}
	
	//INSERT: 회원가입
	public Costomer saveCostomer(Costomer costomer) {
		return costomerRepository.save(costomer);
	}
	
	//SELECT: 자기 자신 정보 보기
	public Optional<Costomer> getCostomerById(String id) {
		return costomerRepository.findById(id);
	}
	
	//DELETE: 회원 탈퇴
	public void deleteMyInfo(String id) {
		costomerRepository.deleteById(id);
	}
}
