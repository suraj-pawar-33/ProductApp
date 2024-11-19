package com.apps.digiple.npdapp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.apps.digiple.npdapp.bean.Product;

public class ProductRespository implements ICustProductRespository{

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Product> findPartial(Integer valueOf) {
//		entityManager.
//		List<Product> list = super.findAll();
//		list.stream().sequential().skip(10 * (valueOf-1)).limit(10);
		return null;
	}

}
