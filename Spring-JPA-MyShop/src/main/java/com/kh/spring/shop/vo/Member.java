package com.kh.spring.shop.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Member") //Member 테이블 만들게요~
@Getter @Setter
public class Member {

	//멤버(필드)변수
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="memberId_seq")
	@SequenceGenerator(name="memberId_seq", sequenceName="memeberId_seq", allocationSize=1)
	@Column(name="member_id")
	private Long memeber_id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
}
