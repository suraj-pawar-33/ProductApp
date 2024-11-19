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

import com.apps.digiple.npdapp.bean.Address;
import com.apps.digiple.npdapp.bean.Bank;
import com.apps.digiple.npdapp.db.IBankRespository;

@Controller
public class BankController {
    @Value("${spring.application.name}")
    String appName;
	
	@Autowired
	IBankRespository bankRespository;	

	@GetMapping("/manage-bank-new")
	public String getAddNewProduct(Model model) {
		model.addAttribute("bank", new Bank());
		model.addAttribute("address", new Address());
		return "manage-bank-new";
	}
	
	/**
	 * 
	 * @param product - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-bank")
	public String getManageBank(@ModelAttribute Bank bank, Model model) {
		return getBanksOnPage("1", bank, model);
	}
	/**
	 * 
	 * @param pageNo
	 * @param bank - filter object
	 * @param model
	 * @return
	 */
	@GetMapping("/manage-bank/page={pageNo}")
    public String getBanksOnPage(@PathVariable String pageNo, @ModelAttribute Bank bank, Model model){
        List<Integer> pageNumList = new ArrayList<>();
		if (pageNo.matches("-?(0|[1-9]\\d*)")) {
			Page<Bank> list;
			boolean isNameBlank = StringUtils.isBlank(bank.getBankName());
			boolean isBarnchBlank = StringUtils.isBlank(bank.getBranch());
			boolean isShortNameBlank = StringUtils.isBlank(bank.getShortName());

			if (!isNameBlank && isBarnchBlank && isShortNameBlank) {
				list = bankRespository.findAllByBankName(bank.getBankName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && !isBarnchBlank && isShortNameBlank) {
				list = bankRespository.findAllByBranch(bank.getBranch(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && isBarnchBlank && !isShortNameBlank) {
				list = bankRespository.findAllByShortName( bank.getShortName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && !isBarnchBlank && isShortNameBlank) {
				list = bankRespository.findAllByBankNameAndBranch(bank.getBankName(),
						bank.getBranch(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (isNameBlank && !isBarnchBlank && !isShortNameBlank) {
				list = bankRespository.findAllByBranchAndShortName(
						bank.getBranch(), bank.getShortName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && isBarnchBlank && !isShortNameBlank) {
				list = bankRespository.findAllByBankNameAndShortName(bank.getBankName(),
						bank.getShortName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else if (!isNameBlank && !isBarnchBlank && !isShortNameBlank) {
				list = bankRespository.findAllByBankNameAndBranchAndShortName(bank.getBankName(),
						bank.getBranch(), bank.getShortName(),
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			} else {
				list = bankRespository.findAll(
						PageRequest.of(Integer.valueOf(pageNo) - 1, 10));
			}
			model.addAttribute("currPage", pageNo);
			for (int i = 1; i <= list.getTotalPages(); i++) {
				pageNumList.add(i);				
			}
	        model.addAttribute("bank", bank);
			model.addAttribute("pageCount", pageNumList);
			model.addAttribute("allBanks", list.getContent());
		}
		return "manage-bank";
    }

}
