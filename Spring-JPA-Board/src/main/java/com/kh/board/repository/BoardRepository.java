package com.kh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}

/*

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