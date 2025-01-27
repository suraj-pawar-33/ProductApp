package com.apps.digiple.npdapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apps.digiple.npdapp.bean.Order2Product;
import com.apps.digiple.npdapp.bean.Orders;
import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.db.IOrder2ProductRespository;
import com.apps.digiple.npdapp.db.IOrderTypeRespository;
import com.apps.digiple.npdapp.db.IOrdersRespository;
import com.apps.digiple.npdapp.db.IProductRespository;
import com.apps.digiple.npdapp.reports.JasperProduct;
import com.apps.digiple.npdapp.reports.ReportPrintHelper;
import com.apps.digiple.npdapp.utils.OrderHelper;

@RestController
@RequestMapping("/api/npd")
public class RestOrdersController {

	@Autowired
	IOrdersRespository orderRespository;
	
	@Autowired
	IOrderTypeRespository orderTypeRespository;
	
	@Autowired
	IOrder2ProductRespository order2productRespository;
	
	@Autowired
	IProductRespository productRespository;
	
	@Autowired
	private MessageSource messageSource;

	@Value("${server.hostname}")
	private String hostname;
	
	@Value("${server.port}")
	private String port;
	


	@PostMapping("/order/update")
	public ModelAndView updateOrder(@ModelAttribute Orders order, Model model){
		model.addAttribute("order", order);
		try {
			Orders newOrder = orderRespository.save(order);
			List<Order2Product> o2pList = OrderHelper.removeEmptyO2PProducts(order);
			for (Order2Product order2Product : o2pList) {
				order2Product.setOrderKey(newOrder.getKey());
				order2productRespository.save(order2Product);
			}
		} catch (Exception e) {
			return getErrorPage(e, model);
		}
		
		Object[] ob = {order.getBillNumber()};
		model.addAttribute("message", messageSource.getMessage("order.msg.create", ob , Locale.ENGLISH));
		model.addAttribute("status", "Created");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-orders");
		return new ModelAndView("status");
	}
	
	@PostMapping("/order/create")
	public ModelAndView createOrder(@ModelAttribute Orders order, Model model){
		model.addAttribute("order", order);

		List<Order2Product> o2pList = OrderHelper.removeEmptyO2PProducts(order);
		Orders newOrder = null;
		try {
			newOrder = orderRespository.save(order);
		}
		catch (Exception e) {
			return getErrorPage(e, model);
		}
		for (Order2Product order2Product : o2pList) {
			try {
				order2Product.setOrderKey(newOrder.getKey());
				order2productRespository.save(order2Product);
			}
			catch (Exception e) {
				return getErrorPage(e, model);
			}
		}
		
		Object[] ob = {order.getBillNumber()};
		model.addAttribute("message", messageSource.getMessage("order.msg.create", ob , Locale.ENGLISH));
		model.addAttribute("status", "Created");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-orders");
		return new ModelAndView("status");
	}
	
	@GetMapping("orders/update/manage-order-delete/{id}")
	public ModelAndView getDeleteOrder(@PathVariable String id, Model model){
		Object[] ob = {id};
		if (id.matches("-?(0|[1-9]\\d*)")) {
			Optional<Orders> entity = orderRespository.findById(Integer.valueOf(id));
			if (entity.isPresent()) {
				
				Object[] ob1 = {entity.get().getBillNumber()};
				List<Order2Product> o2pList = entity.get().getOrder2product();
				for (Order2Product o2p : o2pList) {
					order2productRespository.delete(o2p);
				}
				orderRespository.delete(entity.get());
				model.addAttribute("message", messageSource.getMessage("order.msg.delete", ob1 , Locale.ENGLISH));
				model.addAttribute("status", "Deleted");
				model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-orders");
				return new ModelAndView("status");
			} else {
				model.addAttribute("message", messageSource.getMessage("order.err.delete", ob , Locale.ENGLISH));
				model.addAttribute("status", "Message");
				return new ModelAndView("error");
			}
		} else {
			model.addAttribute("message", messageSource.getMessage("order.err.id", ob , Locale.ENGLISH));
			model.addAttribute("status", "Message");
			return new ModelAndView("error");
		}
	}
    @RequestMapping(value = "/orders/invoice/{id}", method = RequestMethod.GET, produces = {"application/pdf"})
    @ResponseBody
//	@GetMapping(value="/product/download/{id}")
	public FileSystemResource downloadFile(@PathVariable String id) {

		String destPath = "resources/pdf/";
		if (id.matches("-?(0|[1-9]\\d*)")) {
		    Orders order = orderRespository.findByIdOrError(Integer.valueOf(id));
		    Map<String, Object> params = new HashMap<String, Object>();
		    params.put("orderKey", order.getKey());
		    params.put("billNumber", order.getBillNumber());
		    params.put("placedDate", order.getDateOrderPlaced());
		    params.put("bankName", order.getBank().getBankName());
		    params.put("status", order.getStatus().getStatusName());
		    params.put("orderType", order.getOrderType().getOrdTypeName());
		    params.put("totalAmount", order.getTotalAmount());
		    destPath = StringUtils.join(destPath, order.getBillNumber(), ".pdf");
		    Collection<JasperProduct>  list = OrderHelper.LoadJasperProduct(order.getOrder2product());
			ReportPrintHelper.generate(params, list, destPath);
		}
	    return new FileSystemResource(new File(destPath));
	}
	
	private ModelAndView getErrorPage(Exception e, Model model) {
		model.addAttribute("message", e.getLocalizedMessage());
		model.addAttribute("status", "Error");
		return new ModelAndView("error");
	}
    
}
