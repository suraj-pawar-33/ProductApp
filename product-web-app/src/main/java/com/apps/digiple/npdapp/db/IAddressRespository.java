package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Address;



@Repository
public interface IAddressRespository extends PagingAndSortingRepository<Address, Integer> {

	  // custom query to search to bank by name or branch

	List<Address> findAll();

	Address save(Address Address);

}
