package com.kh.springdb.controller;

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
//import org.springframework.web.bind.annotation.RestController;

import com.kh.springdb.service.ProductService;
import com.kh.springdb.vo.Products;
//@RestController //view로 넘어가지 않음! (test용 어노테이션으로 많이 사용)
@Controller
@RequestMapping("/products")
public class ProductController {
	//final로 초기선언해서 변경 방지
	private final ProductService productService;
	
	//생성자
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//모든 제품을 보기 위한 제품 List 확인 메서드
	@GetMapping
	public String getAllProducts(Model model){
		List<Products> products = productService.getAllProducts();
		model.addAttribute("products", products);
		//return productService.getAllProducts();
		return "product_list";
	}
	
	//제품 상세보기 메서드 by product_id
	@GetMapping("/details/{id}")
	public String getProductDetails(@PathVariable("id") Long product_id, Model model) {
		Optional<Products> product = productService.getProductById(product_id);
		//만약 값이 존재한다면~
		product.ifPresent(value -> model.addAttribute("product",value));
		return "product_detail";
	}
	
	//작성한 내용을 저장하기 위한 메서드
	//save @GetMapping 작성할 url을 불러오기위한 주소값 설정
	@GetMapping("/new")
	public String showProductForm(Model model) {
		model.addAttribute("product", new Products());
		return "product_form";
	}
	//save @PostMapping 작성한 내용을 저장할 url 설정
	@PostMapping("/save")
    public String saveProduct(@ModelAttribute Products product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
	
	//수정 메서드...?
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") Long product_id, Model model) {
		Optional<Products> product = productService.getProductById(product_id);
		product.ifPresent(value -> model.addAttribute("product", value));
		return "product_form";
	}
	
	//상품 삭제 메서드
	//delete : @GetMapping
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long product_id) {
		productService.deleteProductById(product_id);
		return "redirect:/products";
	}
}

/*

	@Controller
		어노테이션이 부착된 전통적인 SpringMVC 패턴을 적용한 것
		view를 생성하고 반환하는 역할을 하기도 함
		주로 @RequestMapping과 함께 사용하고 HTTP 요청을 처리하고 그 결과를 View로 보냄
		데이터를 반환할 때는 Model 객체를 통해 View에 데이터를 전달
		
	
	@RestController
		RESTful 웹 서비스를 제공하는데 보다 더 특화된 어노테이션
		@Controller에 @ResponseBody를 함께 사용한 것과 유사하게 동작
		위와 같은 기능들을 편리하게 사용할 수 있도록 조금 더 특수하게 만들어진 어노테이션
		
	~~~ 정리 ~~~
	@Controller : View(html 파일) 반환
	@RestController : @Controller에 @ResponseBody를 추가로 사용하는 것을 대체 가능 > 간결한 코드
	
	@ResponseBody
		메서드가 return해서 반환해야하는 값을 HTTP 응답에서 html로 전달하는 것이 아닌
		java 코드에서 직접 본문으로 전달해서 사용할 수 있는 어노테이션
	
*/