package com.apps.digiple.npdapp.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apps.digiple.npdapp.bean.Bank;



@Repository
public interface IBankRespository extends PagingAndSortingRepository<Bank, Integer> {

	  // custom query to search to bank by name or branch

	List<Bank> findAll();
	
	Page<Bank> findAllByBankName(String bankName, Pageable pagable);
	
	Page<Bank> findAllByBankNameAndBranch(String bankName, String branchName, Pageable pagable);
	
	Page<Bank> findAllByBankNameAndBranchAndShortName(String bankName, String branchName, String shortName, Pageable pagable);

	Bank save(Bank bank);

	Page<Bank> findAllByBranchAndShortName(String branchName, String shortName, PageRequest of);

	Page<Bank> findAllByBranch(String branchName, PageRequest of);

	Page<Bank> findAllByBankNameAndShortName(String bankName, String shortName, PageRequest of);

	Page<Bank> findAllByShortName(String shortName, PageRequest of);

}
