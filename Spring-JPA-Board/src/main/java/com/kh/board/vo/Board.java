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
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="author")
	private String author;
	
	//Getter, Setter by lombok
}

/*

	@SequenceGenerator()
		-데이터베이스에 존재하는 시퀀스와 동일한 이름의 시퀀스를 가져옴
		-존재하지 않을시 > JPA에 시퀀스 생성 권한을 주면, 생성도 가능하다
		-어노테이션의 위치 '직접(변수 바로 위) or 간접(클래스 밖)'
		
	@Lob : Blob, Clob 타입을 매핑
	@CreationTimestamp : INSERT 시 시간을 자동으로 저장
	@UpdateTimestamp : UPDATE 시 시간을 자동으로 저장
	@Temporal : 날짜 타입 매핑
	@CreateDate : 엔티티가 생성되어 저장될 때 시간을 저장
	
	@Column : 테이블 안에 컬럼을 생성하거나, 존재하는 테이블에 컬럼값을 찾아 매핑
		**option
			name : 데이터베이스에서 존재하는 컬럼이름과 자바 클래스에서 존재하는 필드이름이 불일치
					> 자바와 데이터베이스 값이 일치할 수 있도록 매핑
			unique : 유니크 제약 조건 설정
			insertable : insert 가능여부
			updatable : ㅕㅔ==ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ
			length : String 타입의 문자 길이 제약조건 설정
			cloumnDefinition :  데이터베이스 컬럼정보 직접 기술
				@Column(columnDefinition = "varchar(10)
			presision : 큰 값에서 사용할 수 있음 소수점을
			scale(DDL) : 소수점 자리수 Double, float 타입에는 적용X
	
	@GeneratedValue(strategy= GenerationType.)
		GenerationType.AUTO(defalut) : JPA 자동으로 알아서 생성 전략 결정
		GenerationType.SEQUENCE : 데이터베이스 시퀀스를 이용해서 기본키를 생성.
								  간혹 @SequenceGenerator를 사용해서 시퀀스 등록 필요있음
		GenerationType.TABLE : 키 생성용 테이블 사용 (쓸 일 거의 X)
								@TableGenerator 필요
		GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 넘겨줌
									ex) mySQL 데이터베이스의 경우 AUTO_INCREMENT 사용해서 기본키 생성
		
*/