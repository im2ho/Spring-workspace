package com.kh.springdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findCartItemByItemId(int id);
	CartItem findCartItemById(int id); //cartItem.carItem_id
	
	//상품을 장바구니에 추가할 수 있게함 by 상품id로 조회(선택)
	List<CartItem> findByItemId(int itemId);
	
	CartItem findByCartIdAndItemId(Long cartId, int itemId);
}
