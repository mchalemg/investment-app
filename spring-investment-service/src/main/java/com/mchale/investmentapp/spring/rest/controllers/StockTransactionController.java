package com.mchale.investmentapp.spring.rest.controllers;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.rest.services.AccountService;
import com.mchale.investmentapp.spring.rest.services.StockMarketTransactionService;

@RestController
public class StockTransactionController {
	
	@Autowired
	private StockMarketTransactionService service;
	
	@Autowired
	private AccountService accountService;
	
	
	@PostMapping("/transactions/user/{userId}/account/{accountId}")
	public void uploadFidelityCSV(@PathVariable int userId,
			@PathVariable int accountId,
			@RequestParam("file") MultipartFile file) { 

		this.service.loadTransactions(userId, accountId, file);
	}

//	@PostMapping("/transactions/user/{userId}/capitalone/{accountId}")
//	public void uploadCapitalOneCSV(@PathVariable int userId,
//			@PathVariable int accountId,
//			@RequestParam("file") MultipartFile file) { 
//		this.service.loadCapitalOneCSV(userId, accountId, file); 
//	}

}
