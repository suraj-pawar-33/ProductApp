package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Product;

public interface ICustProductRespository {

	List<Product> findPartial(Integer valueOf);
}
