package com.kh.springdb.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
public class UserCheckForm {

	//사용자가 ID나 비밀번호 이메일 회원가입 할 때 필수 입력 사항을 넣어줬는가?
	
	//방법1
	//@NotNull(message="회원 분류 선택은 필수입니다")
	//private UserRole isRole;
		
	//방법2
	@NotEmpty(message="회원 분류 선택은 필수입니다")
	private String isRole;
	
	@Size(min=3,max=25)
	@NotEmpty(message="사용자 id는 필수 입력 사항입니다.")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수 입력 사항입니다")
	private String password1;
	
	@NotEmpty(message="비밀번호 확인은 필수입니다")
	private String password2;
	
	@NotEmpty(message="이메일은 필수 입력 사항입니다")
	@Email
	private String email;
	
}
/*
	@NotNull
		만약 입력한 값이 null일때 메세지가 나올 수 있도록 표시
		-> null 여부 체크
		
	@NotEmpty
		메세지를 예외값으로 발생시킴
		-> empty 예외 체크
*/