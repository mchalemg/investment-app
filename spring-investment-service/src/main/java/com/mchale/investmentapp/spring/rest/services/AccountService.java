package com.mchale.investmentapp.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;

@Component
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	public Account retrieveAccountById(int accountId) { 
		return repository.findByAccountId(accountId);
	}
	
}
