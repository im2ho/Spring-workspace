package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.Cart;
import com.kh.springdb.model.CartItem;
import com.kh.springdb.model.Item;
import com.kh.springdb.model.Order;
import com.kh.springdb.repository.CartItemRepository;
import com.kh.springdb.repository.CartRepository;
import com.kh.springdb.repository.ItemRepository;
import com.kh.springdb.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    //cartId로 CartItem 찾기
    public List<CartItem> findCartItemByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public List<CartItem> findByItemId(int itemId) {
        return cartItemRepository.findByItemId(itemId);
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

	@Transactional
	public void addCart(Long cartId, Item newItem, int amount) {
	    // 현재 담긴 장바구니가 없을 때 장바구니 생성해주는 코드
	    Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
	        Cart newCart = new Cart();
	        return cartRepository.save(newCart);
	    });

	    // 장바구니에 해당 아이템이 이미 담겨져 있는지 확인
	    CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cartId, newItem.getId());

	    if (cartItem == null) {
	        // 장바구니에 해당 아이템이 없으면 새로운 CartItem 생성
	        cartItem = new CartItem();
	        cartItem.setCart(cart);
	        cartItem.setItem(newItem);
	        cartItem.setCount(amount);
	    } else {
	        // 장바구니에 해당 아이템이 이미 담겨져 있으면 수량 증가
	        cartItem.addCount(amount);
	    }

	    // 생성 또는 업데이트된 CartItem을 저장
	    cartItemRepository.save(cartItem);

	}
	
	//결제하기
	@Transactional
	public void checkout(Long cartId) {
		
		//주문할 아이템 정보를 찾기 위해 cart entity 정보를 가져오기
		Cart cart = cartRepository.findById(cartId).orElse(null);
		
		/*
			Cart : 어떤 유저가 장바구니에 물건을 담았는가? (User와 연결)
		 	CartItem : 해당 장바구니에 어떤 물건이 담겨있는가?
		*/
		
		//카트가 null값이 아닐 때
		if(cart != null) {
			//Order 객체를 가져오기
			//Order order = Order + cart(cart) = build()
			Order order = Order.builder().cart(cart).build();
			
			//결제 이후 문제가 생길 것을 대비해서 DB에도 주문내역을 저장
			orderRepository.save(order);
			
			//delete or clear (주문완료 > 장바구니 비우기)
			//cartRepository.deleteAll(); //cart를 지워버리고싶은데..
			cartItemRepository.deleteAll();
			//cart.getCartItems().clear(); 
			//clear() : 배열이나 List, set같은 컬렉션에서 리스트나 컬렉션을 초기화할 때 사용
			//cartRepository.save(cart);
		}
	}

	
	
}

/*public class CartService {

	@Autowired
	private  CartItemRepository cartItemRepository;
	private  ItemRepository itemRepository;
	private  CartRepository cartRepository;
	
	//findCartItemByItemId
	public List<CartItem> findCartItemByCartId(int cartId){
		return cartItemRepository.findCartItemByItemId(cartId);
	}
	
	//findByItemId 
	public List<CartItem> findByItemId(int itemId){
		return cartItemRepository.findByItemId(itemId);
	}
	
	@Transactional //자카르타? 스프링?
	public void addCart(Long cartId, Item newItem, int amount) {
		
		//유저 정보 찾기
		
		//현재 담긴 장바구니에 대한 정보가 없을 때 장바구니 생성해주는 코드
		Cart cart = cartRepository.findById(cartId).orElseGet(()->{
			Cart newCart = new Cart();
			return cartRepository.save(newCart);
		});
		
		//Item item = itemRepository.findById(newItem).orElseThrow(()-> new Exception());
		
		CartItem cartItemNoId = cartItemRepository.findCartIdAndItemId(cartId, amount);
		
		//장바구니 아이템에서 Id값이 존재하지 않을 때, 추가해주는 CartItem
		if(cartItemNoId == null) {
			cartItemNoId = new CartItem();
			cartItemNoId.setCart(cart);
			cartItemNoId.setItem(newItem);
		}
		
		//
		
		//담고자하는 상품의 정보를 가지고 옴 by itemId
		Item item = itemRepository.findItemById(newItem.getId());
		
		//장바구니에 어떤 아이템이 담겨 있는지
		CartItem cartItem = cartItemRepository.findCartItemById(amount);
		
		//만약 장바구니에 상품이 존재 X > 카트를 생성하고 상품을 추가
		
		//장바구니에 상품이 존재한다면 수량만 증가
		CartItem cartUpdate = cartItem;
		cartUpdate.setItem(item);
		cartUpdate.addCount(amount);
		cartUpdate.setCount(cartUpdate.getCount());
		cartItemRepository.save(cartUpdate);
	}
}*/
