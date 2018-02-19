package com.mchale.investmentapp.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;


@RestController
public class AccountController {

	@Autowired
	private AccountRepository repository;

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		Iterable<Account> accounts= repository.findAll();

		for (Account acct : accounts) {
			result += acct.toString() + "<br>";
		}

		return result;
	}

	 
	@RequestMapping("/findByAccountId")
	public String findByAccountId(@RequestParam("accountId") String accountId) {
		String result = "";
		result = repository.findByAccountId(accountId).toString();
		return result;
	}
 
	@RequestMapping("/findByAccountNumber")
	public String findByAccountNumber(@RequestParam("accountNumber") String accountNumber) {
		String result = "";
		result = repository.findByAccountNumber(accountNumber).toString();
		return result;
	}
	

}
