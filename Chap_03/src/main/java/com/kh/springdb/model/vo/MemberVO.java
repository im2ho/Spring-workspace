package com.kh.springdb.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {
	
	//멤버(필드)변수
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userAddr;
	private Date regDate;
	
}
