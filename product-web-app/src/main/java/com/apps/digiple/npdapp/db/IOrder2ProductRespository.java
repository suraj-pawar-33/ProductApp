package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Order2Product;
import com.apps.digiple.npdapp.bean.OrderType;


@Repository
public interface IOrder2ProductRespository extends JpaRepository<Order2Product, Integer> {

	List<Order2Product> findByKeyContaining(int searchTerm);
	
	List<Order2Product> findByOrderKeyContaining(int searchTerm);
	
	List<Order2Product> findByProductKeyContaining(int searchTerm);
	
	List<Order2Product> findAll();

}
