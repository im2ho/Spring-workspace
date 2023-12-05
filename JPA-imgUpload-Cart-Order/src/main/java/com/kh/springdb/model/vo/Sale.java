package com.kh.springdb.model.vo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sale {

	//멤버(필드)변수
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sale_seq")
	@SequenceGenerator(name="sale_seq", sequenceName="sale_seq", allocationSize=1)
	private int id;
	
	//판매자
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="seller_id")
	private User seller;
	
	@OneToMany(mappedBy="sale")
	private List<SaleItem> saleItems = new ArrayList<>();
	
	//총 판매 개수
	private int totalCount;
	
	//판매 정보를 '새로' 생성하는 메서드
	public static Sale createSale(User seller) {
		Sale sale = new Sale();
		sale.setSeller(seller);
		sale.setTotalCount(0);
		return sale;
	}
}

/*

	Sale.java
		판매 정보를 나타내는 객체로, 판매자와 총 판매 개수와 동일한 속성을 지님
		
	SaleItem.java
		판매 정보에 속한 각 상품에 대한 정보를 지님

*/
