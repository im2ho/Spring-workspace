package com.kh.springdb.model;
import lombok.Getter;

@Getter
public enum UserRole {
	//admin
	//나열이 필요한 상수들은 ','을 통해서 작성 후 최종 ;
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	//현재 유저가 ADMIN인지, USER인지 로그인하기 전까지 파악 불가
	//추후 로그인 할 경우 value에 ADMIN 혹은 USER를 넣어줌
	private String value;
	
    UserRole(String value) {
        this.value = value;
    }
    
}

/*
	변수 : 변경 가능
	상수 : 항상 같은 값(변경불가 by final)
	
	enum : 서로 연관된 상수(final)들의 집합
*/