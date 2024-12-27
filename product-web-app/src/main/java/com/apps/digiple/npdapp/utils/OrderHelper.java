package com.apps.digiple.npdapp.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.thymeleaf.util.ListUtils;

import com.apps.digiple.npdapp.bean.Order2Product;
import com.apps.digiple.npdapp.bean.Orders;
import com.apps.digiple.npdapp.bean.Product;

public class OrderHelper {

	public static List<Product> renewProducts(List<Product> content) {
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

	public static List<Order2Product> removeEmptyO2PProducts(Orders order) {
		List<Order2Product> o2pList = order.getOrder2product();
		if (!ListUtils.isEmpty(o2pList)) {
			o2pList.removeIf(order2Product -> order2Product.getQuantity() == 0);
		}
		return o2pList;
	}

	public static List<Order2Product> createO2PProducts(List<Product> content, Orders order) {
		List<Order2Product> o2pList = order.getOrder2product();
		List<Order2Product> list = createO2PProducts(content);
		List<Integer> productKeys = o2pList.stream().map(o2p -> o2p.getProductKey()).collect(Collectors.toList());
		list.forEach(o2p -> {
			if (!productKeys.contains(o2p.getProductKey())) {
				o2pList.add(o2p);
			}
		});
//		if (!ListUtils.isEmpty(o2pList) && !ListUtils.isEmpty(content)) {
//			List<Product> products = o2pList.stream().map(o2p -> o2p.getProduct()).collect(Collectors.toList());
//			content.removeAll(ListUtils.isEmpty(products) ? Collections.EMPTY_LIST : products);
//		}
//		List<Order2Product> list = createO2PProducts(content);
//		for (Order2Product o2p : o2pList) {
//			list.add(o2p);
//		}
		return o2pList;
	}

}
