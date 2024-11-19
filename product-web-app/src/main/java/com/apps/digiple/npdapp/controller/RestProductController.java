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

import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.bean.ProductType;
import com.apps.digiple.npdapp.db.IProductRespository;
import com.apps.digiple.npdapp.db.ProductTypeRespository;

@RestController
@RequestMapping("/api/npd")
public class RestProductController {

	@Autowired
	IProductRespository productRespository;
	
	@Autowired
	ProductTypeRespository productTypeRespository;
	
	@Autowired
	private MessageSource messageSource;

	@Value("${server.hostname}")
	private String hostname;
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/product")
    public List<Product> index(){
		List<Product> list = productRespository.findAll();
        return list;
    }

	
	@GetMapping("/productTypeN")
    public Map<Integer, String> getProductType(){
		List<ProductType> list = productTypeRespository.findAll();
		HashMap<Integer, String> prodType = new HashMap<>();
		list.forEach(ele -> {
			prodType.put(ele.getKey(), ele.getProductTypeName());
		});
		return prodType;
    }
	
	@GetMapping("/productTypeSN")
    public Map<Integer, String> getProductTypeSN(){
		List<ProductType> list = productTypeRespository.findAll();
		HashMap<Integer, String> prodType = new HashMap<>();
		list.forEach(ele -> {
			prodType.put(ele.getKey(), ele.getShortName());
		});
		return prodType;
    }

    @GetMapping("/product/{id}")
    public Optional<Product> show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return productRespository.findById(blogId);
    }
//    
//    @PostMapping("/product/search")
//    public List<Product> search(@RequestBody Map<String, String> body){
//        String searchTerm = body.get("text");
//        return productRespository.findByKeyContaining(searchTerm);
//    }
    
	@PostMapping("/product/create")
	public ModelAndView createProduct(@ModelAttribute Product product, Model model){
		model.addAttribute("product", product);
		productRespository.save(product);
		Object[] ob = {product.getProductName()};
		model.addAttribute("message", messageSource.getMessage("product.msg.create", ob , Locale.ENGLISH));
		model.addAttribute("status", "Created");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-product");
		return new ModelAndView("status");
	}
    
	@PostMapping("/product/update")
	public ModelAndView updateProduct(@ModelAttribute Product product, Model model){
		model.addAttribute("product", product);
		product  = productRespository.save(product);
		Object[] ob = {product.getProductName()};
		model.addAttribute("message", messageSource.getMessage("product.msg.update", ob , Locale.ENGLISH));
		model.addAttribute("status", "Updated");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-product");
		return new ModelAndView("status");
	}
	
	@ModelAttribute("allProductTypes")
	public List<ProductType> populateVarieties() {
		return productTypeRespository.findAll();
	}
	
	@GetMapping("product/update/manage-product-update/{id}")
	public ModelAndView getUpdateProduct(@PathVariable String id, Model model){
		if (id.matches("-?(0|[1-9]\\d*)")) {
			model.addAttribute("product", productRespository.findById(Integer.valueOf(id)));
		} else {
			Object[] ob = {id};
			model.addAttribute("message", messageSource.getMessage("product.err.id", ob , Locale.ENGLISH));
			model.addAttribute("status", "Message");
			return new ModelAndView("error");
		}
		return new ModelAndView("manage-product-update");
	}
	
	@GetMapping("product/update/manage-product-delete/{id}")
	public ModelAndView getDeleteProduct(@PathVariable String id, Model model){
		Object[] ob = {id};
		if (id.matches("-?(0|[1-9]\\d*)")) {
			Optional<Product> entity = productRespository.findById(Integer.valueOf(id));
			if (entity.isPresent()) {
				Object[] ob1 = {entity.get().getProductName()};
				productRespository.delete(entity.get());
				model.addAttribute("message", messageSource.getMessage("product.msg.delete", ob1 , Locale.ENGLISH));
				model.addAttribute("status", "Deleted");
				model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-product");
				return new ModelAndView("status");
			} else {
				model.addAttribute("message", messageSource.getMessage("product.err.delete", ob , Locale.ENGLISH));
				model.addAttribute("status", "Message");
				return new ModelAndView("error");
			}
		} else {
			model.addAttribute("message", messageSource.getMessage("product.err.id", ob , Locale.ENGLISH));
			model.addAttribute("status", "Message");
			return new ModelAndView("error");
		}
	}

}
