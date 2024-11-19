package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.ProductType;


@Repository
public interface ProductTypeRespository extends JpaRepository<ProductType, Integer> {

	  // custom query to search to bank by name or branch

	List<ProductType> findByKeyContaining(String searchTerm);
	
	List<ProductType> findAll();
	

}
