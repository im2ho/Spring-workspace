package com.kh.cafe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafe.repository.CafeRepository;
import com.kh.cafe.vo.Cafe;

@Service
public class CafeService {

	//멤버(필드)변수
	private final CafeRepository cafeRepository;
	
	//생성자
	@Autowired
	private CafeService(CafeRepository cafeRepository) {
		this.cafeRepository = cafeRepository;
	}
	
	//전체 카페 조회 메서드
	public List<Cafe> getAllCafes(){
		return cafeRepository.findAll();
	}
	
	//특정 카페 세부정보 조회
	public Optional<Cafe> getCafeById(Long cafe_id){
		return cafeRepository.findById(cafe_id);
	}
	
	//카페 정보 추가
	public Cafe saveCafe(Cafe cafe) {
		return cafeRepository.save(cafe);
	}
	
	//특정 카페 정보 삭제
	public void deleteCafe(Long cafe_id) {
		cafeRepository.deleteById(cafe_id);
	}
	
	//전체 카페 정보 삭제
	public void deleteAllCafes() {
		cafeRepository.deleteAll();
	}
	
	//특정 검색어로 검색하는 메서드
	public List<Cafe> findCafeByName(String keyword){
		return cafeRepository.findName(keyword);
	}
	
	//특정 검색어로 검색하는 JPA 내장 메서드
	public List<Cafe> findCafeByJPA(String keyword){
		return cafeRepository.findByNameContaining(keyword);
	}
	
	//repository에 작성한 지역counter를 가져와서 이용할 수 있는 메서드
	public int countCafesByLocation(String location) {
		return cafeRepository.countByLocation(location);
	}
	
	//카페 존재여부 확인하는 메서드
	public boolean existCafeByNAME(String name) {
		return cafeRepository.existsByName(name);
	}
}
