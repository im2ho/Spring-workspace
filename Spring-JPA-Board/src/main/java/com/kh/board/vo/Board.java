package com.kh.board.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity //데이터를 넣어주는 곳 명시
@Getter
@Setter
@Table(name="Boards")
public class Board {
	/*
		BOARD_ID	NUMBER(10,0)
		TITLE	VARCHAR2(100 BYTE)
		CONTENT	VARCHAR2(200 BYTE)
		AUTHOR	VARCHAR2(50 BYTE)
	*/
	
	//멤버(필드)변수
	@Id //PK지정
	//sequence와 generator의 순서에는 큰 상관 X
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="board_sequence")
	@SequenceGenerator(name="board_sequence", sequenceName="board_sequence", allocationSize=1)
	@Column(name="board_id") //일케하면 변수값을 컬럼명과 동일하게 안해도 ㄱㅊ
	private Long board_id;
	
	private String title;
	
	private String content;
	
	private String author;
	
	//Getter, Setter by lombok
}

/*

	@SequenceGenerator()
		-데이터베이스에 존재하는 시퀀스와 동일한 이름의 시퀀스를 가져옴
		-존재하지 않을시 > JPA에 시퀀스 생성 권한을 주면, 생성도 가능하다
		-어노테이션의 위치 '직접(변수 바로 위) or 간접(클래스 밖)'

*/