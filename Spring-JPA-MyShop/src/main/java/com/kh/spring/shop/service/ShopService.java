package com.kh.spring.shop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.OrderRepository;
import com.kh.spring.shop.repository.PaymentRepository;
import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Cart;
import com.kh.spring.shop.vo.Order;
import com.kh.spring.shop.vo.Payment;
import com.kh.spring.shop.vo.Product;

@Service
public class ShopService {
	
	//최종적으로 만들어준 order, cart 정보를 통해 > 결제, 주문(장바구니에 상품 추가)을 생성
	
	//멤버(필드)변수
	public Cart cart = new Cart();
	private ProductRepository productRepository;
	private PaymentRepository paymentRepository;
	private OrderRepository orderRepository;
	
	//placeOrder() 메서드..
	public Order placeOrder(Product product, int quantity) {
		//장바구니에 상품 추가
		cart.addToCart(product, quantity);
		
		//주문번호 생성
		Order order = createOrder(product, quantity);
		
		//만약 데이터베이스에 주문 정보를 저장하고 반환할 수 있으므로
		//saveOrder(order);
		
		return order;
	
	}//placeOrder()
	
	//주문 정보 생성 메서드
	public Order createOrder(Product product, int quantity) {
		Order order = new Order();
		order.setProduct(product);
		order.setQuantity(quantity);
		return order;
	} //createOrder()
	
	//상품 정보 조회 메서드 (난 ProductService에도 만들래용)
	public Product getProductById(Long product_id) {
		return productRepository.findById(product_id).orElse(null);
		//Optional을 쓰고싶지 않을 경우
		//return productRepository.findById(product_id).orElse(null);
	}
	
	//주문 정보를 조회 메서드
	public Order getOrderById(Long order_id) {
		//Optional을 쓰고싶지 않을 경우 예시
		return orderRepository.findById(order_id).orElse(null);
	}

	//결제 처리 메서드
	private Payment savePayment(Order order, String payment) {
		//결제 정보 처리
		Payment payments = new Payment();
		payments.setOrder(order);
		payments.setPaymentStatus(payment);
		return payments;
	}
}
