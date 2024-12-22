package com.apps.digiple.npdapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.ListUtils;

import com.apps.digiple.npdapp.bean.Order2Product;
import com.apps.digiple.npdapp.bean.Orders;
import com.apps.digiple.npdapp.bean.Product;

public class OrderHelper {

	public static List<Product>  renewProducts(List<Product> content) {
		for (Product product : content) {
			product.setCost(0);
			product.setSubscriCost(0);
		}
		return content;		
	}

	public static List<Order2Product> createO2PProducts(List<Product> content) {
		List<Order2Product> list = new ArrayList<Order2Product>();
		for (Product product : content) {
			Order2Product o2p = new Order2Product();
			o2p.setProductKey(product.getKey());
			o2p.setProduct(product);
			o2p.setQuantity(0);
			o2p.setAmount(0);
			list.add(o2p);
		}
		return list;	
	}

	public static List<Order2Product>  removeEmptyO2PProducts(Orders order) {
		List<Order2Product> o2pList = order.getOrder2product();
		if (!ListUtils.isEmpty(o2pList)) {
			o2pList.removeIf(order2Product -> order2Product.getAmount() == 0);
		}
		return o2pList;
	}

}
