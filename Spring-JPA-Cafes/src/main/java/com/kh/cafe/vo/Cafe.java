package com.kh.cafe.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Cafe")
public class Cafe {
	
	//DB에 존재하지 않는 테이블 JPA를 활용해서 다 만들어볼게용^^
	
	//멤버(필드)변수
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cafe_seq")
	@SequenceGenerator(name="cafe_seq", sequenceName="cafe_seq", allocationSize=1)
	@Column(name="cafe_id")
	private Long cafe_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(name="opening_hours")
	private String opening_hours;
	
	//GetterSetter 롬복
	
}
