package com.apps.digiple.npdapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.bean.ProductType;
import com.apps.digiple.npdapp.db.IProductRespository;
import com.apps.digiple.npdapp.db.ProductTypeRespository;

@Controller
public class ProductController {
    @Value("${spring.application.name}")
    String appName;

	@Autowired
	ProductTypeRespository productTypeRespository;
	
	@Autowired
	IProductRespository productRespository;
	
	@GetMapping("/manage-product-new")
	public String getAddNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "manage-product-new";
	}
	
	@ModelAttribute("allProductTypes")
	public List<ProductType> populateProductTypes() {
		return productTypeRespository.findAll();
	}
	
//	@ModelAttribute("allProducts")
//	public List<Product> populateProducts() {
//		return productRespository.findAll();
//	}

	/**
	 * 
	 * @param product - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-product")
	public String getManageProduct(@ModelAttribute Product product, Model model) {
		return getProductsOnPage("1", product, model);
	}
	/**
	 * 
	 * @param pageNo
	 * @param product - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-product/page={pageNo}")
    public String getProductsOnPage(@PathVariable String pageNo, @ModelAttribute Product product, Model model){
        List<Integer> pageNumList = new ArrayList<>();
		if (pageNo.matches("-?(0|[1-9]\\d*)")) {
			Page<Product> list;
			boolean isNameBlank = StringUtils.isBlank(product.getProductName());
			boolean isDetailsBlank = StringUtils.isBlank(product.getProductDetails());
			boolean isTypeBlank = product.getProTypeKey() == 0;

			if (!isNameBlank && isDetailsBlank && isTypeBlank) {
				list = productRespository.findAllByProductName(product.getProductName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && !isDetailsBlank && isTypeBlank) {
				list = productRespository.findAllByProductDetails(product.getProductDetails(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && isDetailsBlank && !isTypeBlank) {
				list = productRespository.findAllByProTypeKey( product.getProTypeKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && !isDetailsBlank && isTypeBlank) {
				list = productRespository.findAllByProductNameAndProductDetails(product.getProductName(),
						product.getProductDetails(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && !isDetailsBlank && !isTypeBlank) {
				list = productRespository.findAllByProductDetailsAndProTypeKey(
						product.getProductDetails(), product.getProTypeKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && isDetailsBlank && !isTypeBlank) {
				list = productRespository.findAllByProductNameAndProTypeKey(product.getProductName(),
						product.getProTypeKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && !isDetailsBlank && !isTypeBlank) {
				list = productRespository.findAllByProductNameAndProductDetailsAndProTypeKey(product.getProductName(),
						product.getProductDetails(), product.getProTypeKey(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else {
				list = productRespository.findAll(
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			}
			model.addAttribute("currPage", pageNo);
			for (int i = 1; i <= list.getTotalPages(); i++) {
				pageNumList.add(i);				
			}
	        model.addAttribute("product", product);
			model.addAttribute("pageCount", pageNumList);
			model.addAttribute("allProducts", list.getContent());
		}
		return "manage-product";
    }

}
