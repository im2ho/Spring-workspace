package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.vo.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	//사용자 Id를 바탕으로 id 주인의 카트를 조회
	Cart findByUserId(int id);
	
	//주어진 Cart의 id를 바탕으로 카트 내용 조회
	Cart findCartById(int id);
	
	//카트에서 cart를 중점으로 UserId를 검색해서 조회
	Cart findCartByUserId(int id);
}