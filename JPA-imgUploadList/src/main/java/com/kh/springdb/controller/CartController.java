package com.kh.springdb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.springdb.model.Cart;
import com.kh.springdb.model.CartItem;
import com.kh.springdb.model.Item;
import com.kh.springdb.service.CartService;
import com.kh.springdb.service.ItemService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;
	private final ItemService itemService;
	
	//생성자 : @Autowired와 동일한 기능
	public CartController(CartService cartService, ItemService itemService) {
		this.cartService = cartService;
		this.itemService = itemService;
	}
	
	//장바구니 목록 보여주기 위한 GetMapping
	@GetMapping
	public String viewCart(Model model) {
		Cart cart = cartService.getCartById(1L);
		model.addAttribute("cart", cart);
		return "cartView";
	}
	
	//주소로 접속하기 위한 GetMapping
	@GetMapping("/add/{itemId}")
	public String addToCart(@PathVariable("itemId") int itemId, Model model){
		//장바구니에 상품 추가
		Item newItem = itemService.getItemById(itemId);
		
		
		//addCart(Long cartId, Item newItem, int amount)
		//		> 누구의 장바구니인가 / 새로들어오는 상품 / 총 수량
		cartService.addCart(1L, newItem, 1);
		return "redirect:/cart";
		
		//1L이란? : 임의의 값을 지정할 때 사용하는 표현 (현재 비회원 기준으로 코드 작성 중이므로..)
	}
	@PostMapping("/add")
	public String addToCartItem(@RequestParam("itemId")Long itemId, Model model) {
		Item newItem = itemService.getItemById(itemId.intValue()); //itemId.intValue()
			//1L
		cartService.addCart(1L, newItem, 1);
		return "redirect:/cart";
	}
	
	//결제 완료 후 장바구니 비우기(삭제)
	@PostMapping("/checkout")
	public String checkout(RedirectAttributes redirectAttribute) {
		Long cartId = 1L; //유저 1명을 가정 > 추후 로그인한 유저 값으로 바꿔줘야한다
		try {
			cartService.checkout(cartId);
			redirectAttribute.addFlashAttribute("checkoutStatus","success");
		} catch(Exception e) {
			redirectAttribute.addFlashAttribute("checkoutStatus","empty");
		}
		return "redirect:/cart";
	}
}

/*
	Integer : Wrapper 클래스 객체로 감싸져있기 때문에 null값 가질 수 X
	int : java에서 기본 데이터 타입 정수를 나타내는 값으로, null값 가능
	
	RedirectAttributes
		redirect 할 때 속성을 전달
	
	addFlashAttribute
		데이터를 추가할 때 redirect 후 한 번만 사용 가능
		사용 후 > 속성 자동삭제
		redirect해서 돌아가고자하는 페이지로 이동할 때 속성이 존재하고, 돌아간 페이지에서 속성을 사용 가능
*/
