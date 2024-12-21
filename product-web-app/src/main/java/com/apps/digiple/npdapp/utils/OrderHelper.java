package com.apps.digiple.npdapp.utils;

import java.util.List;

import com.apps.digiple.npdapp.bean.Product;

public class OrderHelper {

	public static List<Product>  renewProducts(List<Product> content) {
		for (Product product : content) {
			product.setCost(0);
			product.setSubscriCost(0);
		}
		return content;		
	}

}
