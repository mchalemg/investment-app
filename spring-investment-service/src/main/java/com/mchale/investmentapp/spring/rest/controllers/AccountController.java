package com.mchale.investmentapp.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.rest.services.AccountService;


@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping ("/accounts/{accountId}")
	public Account retrieveAccount(@PathVariable int accountId) {
		return service.retrieveAccountById(accountId);
	}
	
}
