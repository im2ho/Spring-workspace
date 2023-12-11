package com.kh.springdb.model;

import jakarta.validation.constraints.*;
import lombok.*;

//id나 비밀번호가 적혀있지 않거나, 최대값/최소값을 설정해주기 위해
//form을 체크해주는 class
//무결성 제약 조건에 어긋나는지 확인하는 폼
@Getter @Setter
public class UserCheckForm {

	//멤버(필드)변수
	
	//최대값 최소값을 설정해주고, 빈값일 경우 적용 방지 위해 빈값 관련 문구 설정
	@Size(min=2, max=8)
	@NotEmpty(message="사용자 id는 필수로 입력해야 합니다.")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수로 입력해야 합니다.")
	private String password1;
	
	@NotEmpty(message="비밀번호 확인은 필수로 이루어져야 합니다.")
	private String password2;
	
	@NotEmpty(message="이메일은 필수로 입력해야 합니다.")
	@Email
	private String email;
}
