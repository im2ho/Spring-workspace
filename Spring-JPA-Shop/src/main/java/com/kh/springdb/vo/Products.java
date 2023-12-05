package com.kh.springdb.vo;


//import org.springframework.data.annotation.Id; //NoSQL
//import jakarta.persistence.Id; //SQL

import jakarta.persistence.*;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Products") //와 쩐다! 테이블이 없으면 sql설정으로 테이블도 만들어줌 ㄷㄷ
//이미 DB가 존재할 경우 테이블 명을 지정하지 않으면 됨 (@Table을 써서 테이블 가져오거나, class명으로 맞춰주거나)
@SequenceGenerator(name="product_sequence", sequenceName="product_sequence", allocationSize=1)
//클래스 외부에 어노테이션 : 간접지정
public class Products {

	/*
		PRODUCT_ID	NUMBER(5,0)
		PRODUCT_NAME	VARCHAR2(100 BYTE)
		CATEGORY	VARCHAR2(50 BYTE)
		PRICE	NUMBER(10,2)
		STOCK_QUANTITY	NUMBER(5,0)
	*/
	
	//멤버(필드) 변수-------------------------
	
	@Id //PK지정 (import jakarta.persistence.Id)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="product_id")
	private Long product_id;
	
	//String 데이터 컬럼의 경우, 자동으로 지정한 변수명과 동일한 이름의 컬럼 생성 (name="product_name" 이 생략된 상태)
	//@Column(nullable=false, length=50)
	private String product_name;
	
	//@Column(nullable=false, length=50)
	private String category;
	
	//@Column(name="price")
	private Double price;
	
	private Integer stock_quantity;
	
	//Getter & Setter : lombok 통해서 자동 생성
	
}

/*

	@Table
		테이블 이름을 지정하는 어노테이션
		
	@Id
		해당 필드가 엔터티의 primaryKey임을 나타냄
		
	@Column
		해당 필드가 데이터베이스 테이블의 컬럼에 매핑되는 것을 확인 가능
		- nullable : 해당 컬럼의 null값 허용 여부
		- length : 문자열 컬럼의 최대 길이 지정
		- String으로 시작하는 필드값의 경우, 지정된 변수명으로 컬럼명이 생성되기 때문에 따로 name을 지정해주지 않아도 됨
			(이 외 값은 name을 통해 Column명 지정 필요)
			
	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
	@GeneratedValue : JPA에서 엔터티의 기본 키 값을 자동으로 생성하는 방법을 지정하는데 사용하는 어노테이션
	@SequenceGenerator : GeneratedValue와 연결할 이름을 설정해주고, 시퀀스의 이름과 크기를 지정할 수 있음
		시퀀스를 사용해서 기본키 값을 생성할 수 있도록 지원
		시퀀스는 데이터베이스에서 중복되는 값이 아닌 각 레코드가 고유한 숫자번호를 가질 수 있도록 자동으로 값을 생성
	
	< 예시 >
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
	@SequenceGenerator(name = "product_sequence", 
					sequenceName = "PRODUCT_SEQ", 
					allocationSize = 1)
	
	**MySQL : @GeneratedValue(strategy = GenerationType.IDENTITY)
		데이터베이스 자체에서 자동으로 값이 증가할 수 있도록 자동생성이 들어있는 경우, 위와같은 어노테이션 방식을 사용
		새로운 레코드가 삽입 될 때마다 데이터베이스가 자동으로 기본키의 값을 증가시킴
 
 	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 	
 	Lombok
 		
 		@Builder : 객체를 생성할 때 매개변수의 순서나 개수에 관계없이 보기 편할 수 있도록 객체를 생성할 수 있게 도와주는 메서드
 		
 		@AllArgsConstructor : 모든 필드에 생성자를 생성해줌 by 멤버변수
 		@NoArgsConstructor : 매개변수가 없는 기본 생성자를 생성해줌
 		
 		@RequiredArgsConstructor : 
 			필수로 초기화 해야하는 final 필드나 @NotNull 표시된 필드를 사용하는 생성자를 자동으로 생성해줌
 		
 			예시)
 			@RequiredArgsConstructor
 			public class Student{
 				private final String name;
 				private final int grade;
 				private String address;
 			}
 			> name과 grade는 생성자에 포함되지만 address는 생성자에 포함되지 않음
 			  나중에 Student 객체를 다른데서 불러올 때 Student st = new Student("이름", 5);
 			  address는 넣어주지 않음
 	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 	
 	JPA 어노테이션
 		
 		@Prepersist : 
 			엔터티가 데이터베이스에 저장되기 전에 호출되는 메서드를 지정 > 엔터티에 필요한 사전 처리 작업을 수행하는데 많이 사용된다
 			
 			예시)
 			@Entity
 			public class TestEntity{
 				
 				@Id
 				private Long id,
 				private String name;
 				private Date createDate; 
 				
 				@Prepersists
 				private void preWork(){
 				
 				}
 			}
 				
 		
*/
