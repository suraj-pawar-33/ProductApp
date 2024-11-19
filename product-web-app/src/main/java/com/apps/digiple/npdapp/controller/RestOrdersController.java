package com.apps.digiple.npdapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apps.digiple.npdapp.bean.Orders;
import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.bean.ProductType;
import com.apps.digiple.npdapp.db.IOrderTypeRespository;
import com.apps.digiple.npdapp.db.IOrdersRespository;
import com.apps.digiple.npdapp.db.IProductRespository;
import com.apps.digiple.npdapp.db.ProductTypeRespository;

@RestController
@RequestMapping("/api/npd")
public class RestOrdersController {

	@Autowired
	IOrdersRespository orderRespository;
	
	@Autowired
	IOrderTypeRespository orderTypeRespository;
	
	@Autowired
	private MessageSource messageSource;

	@Value("${server.hostname}")
	private String hostname;
	
	@Value("${server.port}")
	private String port;
	


	@PostMapping("/order/create")
	public ModelAndView createProduct(@ModelAttribute Orders order, Model model){
		model.addAttribute("product", order);
		orderRespository.save(order);
		Object[] ob = {order.getBillNumber()};
		model.addAttribute("message", messageSource.getMessage("order.msg.create", ob , Locale.ENGLISH));
		model.addAttribute("status", "Created");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-product");
		return new ModelAndView("status");
	}
    
}
