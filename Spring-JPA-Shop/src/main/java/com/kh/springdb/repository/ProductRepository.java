package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.vo.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{
	
}

/*

	//메서드 이름으로 간단하게 쿼리 정의
	Product findByProductName(String porduct_name);
	
	//전체 제품 출력하는 메서드 추가
	List<Product> findByPridceDesc(int Price);
	
	//만약 repository에 쿼리를 추가하고싶을 경우
	@Query("SELECT * FROM ProductItem WHERE price")
	List<Product> findByDetail(@Param("category") String category);
								//@Param import주의(ibatis X)

	
	public void CreateProductList() {
		Product product = new Product();
		
		product.setProduct_name("테스터 제품");
		product.setPrice(1000);
		product.setCategory("가전");
	} //CreateProductList()

	++++++++++++++++++++++++++++++++++++++++++++

	Repository
		Spring Data JPA에서 제공하는 인터페이스
		DB에서 User 엔터티에 접근하는데 사용
		기본적인 CRUD 작업을 수행할 수 있는 메서드를 제공
		
		ex)
			조회 : 전체조회(findAll) / 아이디 하나만 조회(findById)
			저장 : save
			삭제 : 부분삭제(deleteById)

*/