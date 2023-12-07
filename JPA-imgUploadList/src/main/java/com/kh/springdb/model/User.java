package com.kh.springdb.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
/*
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity*/
public class User {
	/*
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	private int id;
	
	//닉네임 중복 방지를 위해 제약조건
	@Column(unique=true)
	private String userName;
	
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String role; //회원 및 관리자
	
	private int coin;
	
	//판매자가 가지고있는 상품
	private List<Item> items = new ArrayList<>();
	
	//판매자가 판매한 내역 > Sale 생성 후 주석 해제
	//@OneToMany(mappedBy = "seller")
	//private List<Sale> sellerSale;
	
	//판매자가 판매한 상품들 > SaleItem 생성 후 주석 해제
	//@OneToMany(mappedBy = "seller")
	//private List<SaleItem> sellerSaelItem = new ArrayList<>();
	
	//구매자 결제 내역
	
	//구매자 주문 리스트
	
	//구매자가 사고싶은 내역의 장바구니*/
}
