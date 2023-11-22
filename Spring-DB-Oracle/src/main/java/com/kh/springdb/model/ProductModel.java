package com.kh.springdb.model;

public class ProductModel {
	
	/*
		PRODUCT_ID	NUMBER(5,0)
		PRODUCT_NAME	VARCHAR2(100 BYTE)
		CATEGORY	VARCHAR2(50 BYTE)
		PRICE	NUMBER(10,2)
		STOCK_QUANTITY	NUMBER(5,0)
	 */

	//멤버변수
	private int product_id;
	private String product_name;
	private String category;
	private double price;
	private int stock_quantity;
	
	//Getter & Setter-----------------------------
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	
}
