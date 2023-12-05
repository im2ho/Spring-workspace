package com.kh.springdb.model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_seq")
	@SequenceGenerator(name="item_seq", sequenceName="item_seq", allocationSize=1)
	private int id;
	//상품명
	private String name;
	//상품설명
	private String text;
	//상품가격
	private int price;
	//판매개수
	private int count;
	//재고
	private int stock;
	//판매 상태
	private boolean isSoldout;
	
	//상품을 판매하는 판매자가 여러명일 수 있으므로 판매자가 누구인지 아이디를 넣어준다
	//판매자 아이디(admin)
	@ManyToOne //판매자 한 명이 여러 개의 상품을 팔 수 있기 때문에 1:N 관계
	@JoinColumn(name="seller_id")
	private User seller;
	
	@OneToMany(mappedBy ="item")
	private List<CartItem> cartItems = new ArrayList<>();
	

	//이미지 파일명
	private String imgName;
	//상품 이미지 경로
	private String imgPath;
	
	// 상품 등록 날짜
	@DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;
	
	// DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
	@PrePersist
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}