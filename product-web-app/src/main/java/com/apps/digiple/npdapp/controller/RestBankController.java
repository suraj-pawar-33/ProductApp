package com.apps.digiple.npdapp.controller;

import java.util.List;
import java.util.Locale;
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

import com.apps.digiple.npdapp.bean.Address;
import com.apps.digiple.npdapp.bean.Bank;
import com.apps.digiple.npdapp.bean.Product;
import com.apps.digiple.npdapp.db.IAddressRespository;
import com.apps.digiple.npdapp.db.IBankRespository;

@RestController
@RequestMapping("/api/npd")
public class RestBankController {

	@Autowired
	IBankRespository bankRespository;

	@Autowired
	IAddressRespository addressRespository;

	@Autowired
	private MessageSource messageSource;

	@Value("${server.hostname}")
	private String hostname;
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/bank")
    public List<Bank> index(){
		List<Bank> list = bankRespository.findAll();
        return list;
    }

    @GetMapping("/bank/{id}")
    public Optional<Bank> show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return bankRespository.findById(blogId);
    }
    
	@PostMapping("/bank/create")
	public ModelAndView createBank(@ModelAttribute Bank bank, @ModelAttribute Address address, Model model){
		model.addAttribute("bank", bank);
		Address savedAddress = addressRespository.save(address);
		bank.setAddress(savedAddress);
		System.out.println(savedAddress.getKey());
		bankRespository.save(bank);
		Object[] ob = {bank.getBankName()};
		model.addAttribute("message", messageSource.getMessage("bank.msg.create", ob , Locale.ENGLISH));
		model.addAttribute("status", "Created");
		model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-bank");
		return new ModelAndView("status");
	}

	@GetMapping("bank/update/manage-bank-delete/{id}")
	public ModelAndView getDeleteBank(@PathVariable String id, Model model){
		Object[] ob = {id};
		if (id.matches("-?(0|[1-9]\\d*)")) {
			Optional<Bank> entity = bankRespository.findById(Integer.valueOf(id));
			if (entity.isPresent()) {
				Object[] ob1 = {entity.get().getBankName()};
				bankRespository.delete(entity.get());
				model.addAttribute("message", messageSource.getMessage("bank.msg.delete", ob1 , Locale.ENGLISH));
				model.addAttribute("status", "Deleted");
				model.addAttribute("link", "http://" + hostname+ ":" + port + "/manage-bank");
				return new ModelAndView("status");
			} else {
				model.addAttribute("message", messageSource.getMessage("bank.err.delete", ob , Locale.ENGLISH));
				model.addAttribute("status", "Message");
				return new ModelAndView("error");
			}
		} else {
			model.addAttribute("message", messageSource.getMessage("bank.err.id", ob , Locale.ENGLISH));
			model.addAttribute("status", "Message");
			return new ModelAndView("error");
		}
	}

}
