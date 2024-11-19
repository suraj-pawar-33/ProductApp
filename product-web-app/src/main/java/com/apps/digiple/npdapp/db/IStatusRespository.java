package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Status;


@Repository
public interface IStatusRespository extends JpaRepository<Status, Integer> {

	List<Status> findByKeyContaining(String searchTerm);
	
	List<Status> findAll();

}
