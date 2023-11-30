package com.kh.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	//게이판에서 제목에 특정 키워드가 포함된 게시물을 검색
	@Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword%")
	List<Board> findTitle(@Param("keyword") String keyword);
}

/*
	JPA 제공 메서드
	
	findAll(), findById()
	save()
	deleteById()
	
	======================
	
	findOne() : 조건에 해당하는 엔터티 중에서 첫 번째 엔터티만 반환
				조건에 해당하는 엔터티가 존재하지 않을시 null을 반환
		
	findById() : 일반적으로 PK에 해당하는 엔터티를 찾아서 반환
		Optional Optional.empty() = 빈 값으로 반환 가능
		JPA2.0 이후로 출시된 findOne() 보다는 최신 기능임다 히히

*/

/*
	쿼리를 직접 사용해보자!
	
	@Query : JPA에서 제공하는 CRUD 쿼리 이외에 추가적으로 필요한 쿼리가 있을 경우,
			 직접 생성해서 쿼리를 정의할 때 사용하는 어노테이션
			 인터페이스 메서드에 직접 쿼리를 작성할 수 있으며 더 복잡한 검색이나 조인 등의 작업 수행 가능
	
	ex)
		@Query("SELECT * FROM board WHERE title LIKE %:keyword%")
		List<Board> findTitle(@Param("Keyword") String keyword);
			
			%:keyword%
				keyword 파라미터로 받아온 키워드를 나타냄
				(% : 어떤 문자열이라도 매칭이 될 수 있도록 도와줌)
			List<Board> : 검색 결과를 리스트 형태로 반환할 수 있도록 해줌
			@Param("Keyword") : Param이라는 어노테이션 사용
				메서드에서 매개변수로 전달된 keyword의 값을 쿼리 내에 :ㅏㄷ
	
	========================================================================
	
	@Query("SELECT b FROM board b WHERE b.title LIKE %:keyword%")
		board b: b를 붙이는 것은 엔티티에서 별칭을 지정해서 사용하는 방식
	
	JPQL(Java Persistence Query Language)
		자바객체를 대상으로 하는 쿼리 (JPA에서 사용)
		엔티티 객체와 필드에 대한 쿼리를 정의하는데 사용
		
		JPQL 엔티티와 필드에 대한 쿼리를 작성할 때 SQL과는 조금 다른 문법을 사용
*/