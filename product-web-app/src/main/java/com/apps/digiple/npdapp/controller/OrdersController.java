package com.apps.digiple.npdapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.apps.digiple.npdapp.bean.Address;
import com.apps.digiple.npdapp.bean.Bank;
import com.apps.digiple.npdapp.bean.Order2Product;
import com.apps.digiple.npdapp.bean.OrderType;
import com.apps.digiple.npdapp.bean.Orders;
import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.bean.ProductType;
import com.apps.digiple.npdapp.bean.Status;
import com.apps.digiple.npdapp.db.IBankRespository;
import com.apps.digiple.npdapp.db.IOrderTypeRespository;
import com.apps.digiple.npdapp.db.IOrdersRespository;
import com.apps.digiple.npdapp.db.IProductRespository;
import com.apps.digiple.npdapp.db.IStatusRespository;
import com.apps.digiple.npdapp.utils.OrderHelper;

@Controller
public class OrdersController {
    @Value("${spring.application.name}")
    String appName;
	
	@Autowired
	IOrdersRespository ordersRespository;	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	IOrderTypeRespository orderTypeRespository;	
	
	@Autowired
	IStatusRespository statusRespository;	
	
	@Autowired
	IBankRespository bankRespository;
	
	@Autowired
	IProductRespository productRespository;
	
	@ModelAttribute("allProducts")
	public List<Product> populateProducts() {
		return productRespository.findAll();
	}
	
	@GetMapping("/manage-order-new")
	public String getAddNewOrder(Model model) {
		return getAddNewOrder("1", model);
	}

	@GetMapping("/manage-order-new/product={pageNo}")
	public String getAddNewOrder(@PathVariable String pageNo, Model model) {
		List<Integer> pageNumList = new ArrayList<>();
		Orders order = new Orders();
		if (pageNo.matches("-?(0|[1-9]\\d*)")) {
			Page<Product> list;
			list = productRespository.findAll(
					PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			for (int i = 1; i <= list.getTotalPages(); i++) {
				pageNumList.add(i);				
			}
			List<Order2Product> orderProducts = OrderHelper.createO2PProducts(list.getContent());
			order.setOrder2product(orderProducts);
			model.addAttribute("pageCount", pageNumList);
			model.addAttribute("currPage", pageNo);
		}
		model.addAttribute("order", order);
		return "manage-order-new";
	}
	
	@GetMapping("/manage-order-update/product={pageNo},{id}")
	public String getUpdateProduct(@PathVariable String id, @PathVariable String pageNo, Model model){

		List<Integer> pageNumList = new ArrayList<>();
		if (id.matches("-?(0|[1-9]\\d*)") && pageNo.matches("-?(0|[1-9]\\d*)")) {
			Orders order = ordersRespository.findByIdOrError(Integer.valueOf(id));
			Page<Product> list;
			list = productRespository.findAll(
					PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			List<Order2Product> orderProducts = OrderHelper.createO2PProducts(list.getContent(), order);
			order.setOrder2product(orderProducts);
			for (int i = 1; i <= list.getTotalPages(); i++) {
				pageNumList.add(i);				
			}
			model.addAttribute("pageCount", pageNumList);
			model.addAttribute("currPage", pageNo);
			model.addAttribute("order", order);
			model.addAttribute("id", id);
		} else {
			Object[] ob = {id};
			model.addAttribute("message", messageSource.getMessage("order.err.id", ob , Locale.ENGLISH));
			model.addAttribute("status", "Message");
			return "error";
		}
		
		return "manage-order-update";
	}


	@ModelAttribute("allOrderTypes")
	public List<OrderType> populateOrderTypes() {
		return orderTypeRespository.findAll();
	}

	@ModelAttribute("allStatuses")
	public List<Status> populateStatuses() {
		return statusRespository.findAll();
	}

	@ModelAttribute("allBanks")
	public List<Bank> populateBanks() {
		return bankRespository.findAll();
	}
	
	/**
	 * 
	 * @param product - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-orders")
	public String getOrders(@ModelAttribute Orders orders, Model model) {
		return getOrders("1", orders, model);
	}
	/**
	 * 
	 * @param pageNo
	 * @param bank - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-orders/page={pageNo}")
    public String getOrders(@PathVariable String pageNo, @ModelAttribute Orders orders, Model model){
        List<Integer> pageNumList = new ArrayList<>();
		if (pageNo.matches("-?(0|[1-9]\\d*)")) {
			Page<Orders> list;
			boolean isBillBlank = orders.getBillNumber() == 0;
			boolean isBankBlank = orders.getBankKey() == 0;
			boolean isStatusBlank = orders.getStatusKey() == 0;

			if (!isBillBlank && isBankBlank && isStatusBlank) {
				list = ordersRespository.findAllByBillNumber(orders.getBillNumber(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isBillBlank && !isBankBlank && isStatusBlank) {
				list = ordersRespository.findAllByBankKey(orders.getBankKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isBillBlank && isBankBlank && !isStatusBlank) {
				list = ordersRespository.findAllByStatusKey( orders.getStatusKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isBillBlank && !isBankBlank && isStatusBlank) {
				list = ordersRespository.findAllByBillNumberAndBankKey(orders.getBillNumber(),
						orders.getBankKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isBillBlank && !isBankBlank && !isStatusBlank) {
				list = ordersRespository.findAllByBankKeyAndStatusKey(
						orders.getBankKey(), orders.getStatusKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isBillBlank && isBankBlank && !isStatusBlank) {
				list = ordersRespository.findAllByBillNumberAndStatusKey(orders.getBillNumber(),
						orders.getStatusKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isBillBlank && !isBankBlank && !isStatusBlank) {
				list = ordersRespository.findAllByBillNumberAndBankKeyAndStatusKey(orders.getBillNumber(),
						orders.getBankKey(), orders.getStatusKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else {
				list = ordersRespository.findAll(
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			}
			model.addAttribute("currPage", pageNo);
			for (int i = 1; i <= list.getTotalPages(); i++) {
				pageNumList.add(i);				
			}
	        model.addAttribute("order", orders);
			model.addAttribute("pageCount", pageNumList);
			model.addAttribute("allOrders", list.getContent());
		}
		return "manage-orders";
    }

}
