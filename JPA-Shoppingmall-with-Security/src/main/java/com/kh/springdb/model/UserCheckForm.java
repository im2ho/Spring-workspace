package com.kh.springdb.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
public class UserCheckForm {

	@Size(min=2,max=8)
	@NotEmpty(message="사용자 id는 필수 입력 사항입니다.")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수 입력 사항입니다")
	private String password1;
	
	@NotEmpty(message="비밀번호 확인이 필요합니다")
	private String password2;
	
	@NotEmpty(message="이메일을 필수 입력 사항입니다")
	@Email
	private String email;
}
