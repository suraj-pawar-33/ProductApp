package com.apps.digiple.npdapp.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Product;


@Repository
public interface IProductRespository extends PagingAndSortingRepository<Product, Integer> {

	  // custom query to search to product by name or type
	
	List<Product> findAll();
	
	Page<Product> findAllByProductName(String productName, Pageable pagable);
	
	Page<Product> findAllByProductNameAndProductDetails(String productName, String productDetails, Pageable pagable);
	
	Page<Product> findAllByProductNameAndProductDetailsAndProTypeKey(String productName, String productDetails, int proType, Pageable pagable);

	Product save(Product product);

	Page<Product> findAllByProductDetailsAndProTypeKey(String productDetails, int proTypeKey, PageRequest of);

	Page<Product> findAllByProductDetails(String productDetails, PageRequest of);

	Page<Product> findAllByProductNameAndProTypeKey(String productName, int proTypeKey, PageRequest of);

	Page<Product> findAllByProTypeKey(int proTypeKey, PageRequest of);
	
}
