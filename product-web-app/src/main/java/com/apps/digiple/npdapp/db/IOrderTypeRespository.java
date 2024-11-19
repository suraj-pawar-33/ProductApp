package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.OrderType;


@Repository
public interface IOrderTypeRespository extends JpaRepository<OrderType, Integer> {

	List<OrderType> findByKeyContaining(String searchTerm);
	
	List<OrderType> findAll();

}
