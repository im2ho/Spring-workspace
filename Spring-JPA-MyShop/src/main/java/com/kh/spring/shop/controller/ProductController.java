package com.kh.spring.shop.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.shop.service.ProductService;
import com.kh.spring.shop.vo.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

	//멤버(필드)변수
	private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
	
	//상품 조회
	@GetMapping
	public String getAllProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products",products);
		
		return "product_list";
	}
	
	//상품 상세 정보 조회
	@GetMapping("/details/{id}")
	public String getProductDetails(@PathVariable("id") Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		product.ifPresent(value -> model.addAttribute("product",value));
		return "product_detail";
	}
	
	//상품 등록 & 등록 결과 : @GetMapping & @PostMapping
	@GetMapping("/new")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product/product_form";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	//상품정보 수정
	@GetMapping("/update/{id}")
	public String getUpdateProduct(@PathVariable("id") Long id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		product.ifPresent(value -> model.addAttribute("product",value));
		return "product_form";
	}
	
	//삭제
	@GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
