package com.kh.springdb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	
	//멤버(필드)변수
	private int product_id;
	private String product_name;
	private String category;
	private int price;
	private int stock_quantity;
	
}