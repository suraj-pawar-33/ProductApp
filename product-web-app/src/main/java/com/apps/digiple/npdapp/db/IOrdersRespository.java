package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Orders;

@Repository
public interface IOrdersRespository extends PagingAndSortingRepository<Orders, Integer> {

	  // custom query to search orders by bill number
//	TODO : add type bank and status
	
	List<Orders> findAll();
	
	Page<Orders> findAllByBillNumber(int billNumber, Pageable pagable);

	Orders save(Orders product);

	Page<Orders> findAllByBankKey(int bankKey, PageRequest of);

	Page<Orders> findAllByStatusKey(int statusKey, PageRequest of);

	Page<Orders> findAllByBillNumberAndBankKey(int billNumber, int bankKey, PageRequest of);

	Page<Orders> findAllByBankKeyAndStatusKey(int bankKey, int statusKey, PageRequest of);

	Page<Orders> findAllByBillNumberAndStatusKey(int billNumber, int statusName, PageRequest of);

	Page<Orders> findAllByBillNumberAndBankKeyAndStatusKey(int billNumber, int bankKey, int statusKey,
			PageRequest of);
	
}
