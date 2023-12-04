package com.kh.springdb.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="Costomer")
public class Costomer {

	//멤버(필드)변수
	@Id
	private String id;
	private String nickname;
	private String password;
	private String email;
}