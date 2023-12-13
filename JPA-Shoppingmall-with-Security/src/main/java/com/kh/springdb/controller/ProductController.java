package com.kh.springdb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.service.CommentService;
import com.kh.springdb.service.ProductService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	//멤버(필드)변수
	private final ProductService productService;
	private final CommentService commentService;

	@GetMapping("/")
	public String mainPageView(Model model) {
		return "index";
	}
	
	//페이지네이션 체크를 위한 GetMapping 추가
	@GetMapping("/list")
	public String pageList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		//@RequestParam : 어떤 값으로 요청을 할지 지정
		//@RequestParam(value="page", defaultValue="0")
			//value="page" 값으로 page 이름을 받을 것
			//만약 초반, 후반에 어떤 값이 page 내에 존재하지 않을 경우, 기본 값을 0으로 설정해서 초기값이 null이 아닌 0이 됨
			//페이지는 배열값으로 0이지만, 변수에는 추후 1이 할당되도록 할 것
			//-> 페이지에는 1로 표기가 되지만 코드 내에서는 0부터 시작하는 것으로 표기 됨
		Page<Product> paging = productService.getList(page); //paging.number : 0,1,2,3,4,5,6
		model.addAttribute("paging",paging);
		return "productList";
	}
	
	//전체 상품 목록 페이지
	@GetMapping("/product/list")
	public String productList(Model model) {
		//아이템을 추가한 서비스를 불러와서 모델이 넣어주기
		List<Product> products = productService.allProductView();
		model.addAttribute("products", products);
		return "product_list";
	}
	
	//상품 등록 페이지(조회)
	@GetMapping("/product/new")
	public String productSaveForm(Model model) {
		return "product_form";
	}
	//등록된 상품 업로드
	@PostMapping("/product/new")
	public String productSave(Product products, MultipartFile imgFile) throws IllegalStateException, IOException {
		productService.saveProduct(products, imgFile);
		return "redirect:/product/list"; 
	}
	
	//상품 클릭했을 때 상세보기 메서드
	@GetMapping("/product/detail/{id}")
	public String productDetail(@PathVariable("id") int id, Model model) {
		//상세보기를 검색할 조건
		Product product = productService.getProductById(id);
		//하나의 아이디 값을 가지고와서 지정된 제품을 모든 내용을 보여줄 수 있도록
		//"product" templates 밑에서 thymeleaf로 불러올 변수명을 product로 지정
		//Product product 만들어준 필드명을 가져와서 service로 불러온 내용을 "product"안에 저장해줌
		model.addAttribute("product", product);
		return "product_detail";
	}
	
	//댓글 작성 위한 PostMapping
	@PostMapping("/addComment")
	public String addComment(@RequestParam(value="productId") int productId, @RequestParam(value="commentContent") String commentContent) {
		commentService.addComment(productId, commentContent);
		return "redirect:/product/detail/" + productId;
	}
	
	//댓글 삭제
	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable("id") Long id) {
		commentService.deleteComment(id);
		return "redirect:/product/list";
	}
	
	
	//찜(좋아요)한 내용을 받을 수 있는 PostMapping
	//@PostMapping("/like")
	//public String like(int productId) {
	//	productService.likeProduct(productId);
	//	return "redirect:/product/list";
	//}
	
	
}