package com.kh.springdb.model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
//모든 필드값을 파라미터로 받을 수 있는 생성자 생성
@AllArgsConstructor //Admin ad = new Admin(Admin 클래스 안에 있는 필드 모두 생성);
//파라미터가 없는 기본 생성자 생성
@NoArgsConstructor //Admin ad = new Admin();
@Getter 
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	private int id;
	
	@Column(unique=true) //닉네임 중복되지 않도록 설정
	private String username; 
	
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String role; //관리자인지 확인하기 위한 권한 설정
	
	//충전금 for 구매자
	//판매액 for 판매자
	private int coin;
	
	//판매자가 가지고 있는 상품들
	@OneToMany(mappedBy = "id")
	private List<Item> items = new ArrayList<>();
	
	/***** 구매자를 위한 필드 *****/
	
	//구매자용 장바구니
	@OneToOne(mappedBy="user")
	private Cart cart;
	
	//구매자의 주문 리스트
	@OneToMany(mappedBy = "user")
	private List<Order> userOrder = new ArrayList<>();
	
	//구매자가의 주문 상품 목록
	@OneToMany(mappedBy = "user")
	private List<OrderItem> userOrderItem = new ArrayList<>();
	
	/***** 판매자를 위한 필드 *****/
	
	//판매자의 상품리스트
	@OneToMany(mappedBy = "seller")
	private List<SaleItem> sellerSaleItem = new ArrayList<>();
	
	//판매 리스트
	@OneToMany(mappedBy="seller")
	private List<Sale> sale;
	
	//주문날짜와 판매날짜
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
	//DB에 판매자나 구매자가 구매한 기록보다 먼저 날짜가 들어가면 안되기 때문에
	//미리 데이터베이스에 넣어줄 날짜 값을 생성
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
}
