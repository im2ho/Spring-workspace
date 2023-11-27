package com.kh.springdb.model;

public class Board {
	
	/*
	 	BOARD_ID	NUMBER(10,0)
		TITLE	VARCHAR2(100 BYTE)
		CONTENT	VARCHAR2(200 BYTE)
		AUTHOR	VARCHAR2(50 BYTE)
	 */
	
	//멤버변수
	int board_id;
	String title;
	String content;
	String author;
	
	//Getter-------------------
	
	public int getBoard_id() {
		return board_id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAuthor() {
		return author;
	}
	
	//Setter-------------------
	
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
