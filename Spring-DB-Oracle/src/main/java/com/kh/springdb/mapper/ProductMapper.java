package com.kh.springdb.mapper;

import java.util.List;
import com.kh.springdb.model.ProductModel;


public interface ProductMapper {
	
	List<ProductModel> getAllProducts();
}