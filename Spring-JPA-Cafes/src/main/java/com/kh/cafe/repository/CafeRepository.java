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
	
	//---------------------------------------------------
	
	//count를 이용해서 지역별 카페 개수가 몇 개인지 찾아보는 메서드
	int countByLocation(String location);
	
	//---------------------------------------------------
	
	//카페의 존재여부 확인(boolean)
	boolean existsByName(String name);
}

/*

	Query Creation : 
		Spring Data JPA에서 제공하는 기능으로,
		메서드에 규칙이 존재하고 규칙에 따라서 메서드를 생성해준다.
		메서드 이름만으로 데이터베이스 쿼리 생성이 가능하다!
		
	< JPA 규칙 >
		VO(Model)에 존재하는 멤버(변수) 중...
		
		private String name;
		private String location;
		private String openingHours;
		
	1) findBy + 내가찾고싶은변수명
		
		지역을 검색하고 싶다면
			findByLocation(String location)
			=> SELECT * FROM Cafe WHERE Location = ?
				
			findByNameContaining(String keyword);
			(Containing => 해당하는 변수명이 특정 변수에 대한 검색을 LIKE로 진행할 수 있게 도와줌)
				
		운영시간을 검색하고 싶다면
			findByOpeningHours(String openingHours)
			=> SELECT * FROM Cafe WHERE openingHours = ?
				
	2) countBy + 클래스에 적어준 변수명 : 총 개수를 계산	
	
		countByLocation(String Location)
			=> SELECT COUNT(*) FROM Cafe WHERE location = ?
		
	3) existsBy + 클래스에 적어준 변수명 : 존재 여부를 확인해주는 메서드
		
		existsByLocation(String Location)
			=> SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false
				END FROM Cafe WHERE location =?
	
	4) deleteBy + 클래스에 적어준 변수명 : 삭제
		
		deleteByLocation(String Location)
			=> DELETE FROM Cafe WHERE location = ?
			
	============================================================
	
	< JPA에서 SQL 구문 활용 >
	
	AND / OR / IS / EQUALS / BETWEEN 등등.. 비교연산자 및 기타 구문 사용법
			 
	1) AND 구문
		findBy + 변수명1 + AND + 변수명2
		
	2) OR 구문
		findBy + 변수명1 + OR + 변수명2
		
	3) IS 또는 EQUALS 구문
		findBy + 변수명 + IS(Equals)
	
	4) BETWEEN / AFTER / BEFORE / LIKE 구문
		findBy + 변수명 + Between(그 외)
		
	5) OrderBy 정렬 구문
		findBy + 변수명 + OrderBy + 정렬기준변수명 + Asc(Desc)
		
	6) IN 구문
		findBy + 변수명 + In(List<데이터타입 및 예약어> 변수명)
		ex) findByNameIn(List<String> Name)
	
	7) TRUE / FALSE 구문
		findBy + 변수명 + True(False)
		= SQL : WHERE 테이블명의단축어.변수명 = true(혹은 false)
		
	8) IgnoreCase 구문
		findBy + 변수명 + IgnoreCase
		
*/