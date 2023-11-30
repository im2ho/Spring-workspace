package com.kh.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.cafe.vo.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long>{
	
	//카페목록 중 상호명에 특정 키워드가 포함된 곳을 검색
	@Query("SELECT c FROM Cafe c WHERE c.name LIKE %:keyword%")
	//보여줄 목록이 여러개 > List로 담아서 한 번에 보이기
	List<Cafe> findName(@Param("keyword") String keyword);
	
	//---------------------------------------------------
	
	//JPA에 존재하는 검색 전용 쿼리가 이미 존재한다..!
	//특정 문자열을 포함한 엔터티를 검색하는데 사용하는 메서드 (자체에 일부 검색도 가능한 쿼리가 포함되어있다!)
	List<Cafe> findByNameContaining(String keyword);
	
}