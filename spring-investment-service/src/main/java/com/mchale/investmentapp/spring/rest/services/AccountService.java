package com.mchale.investmentapp.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.spring.data.cache.AccountCache;
import com.mchale.investmentapp.spring.data.cache.StockMarketAssetCache;
import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;

@Component
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	public Account retrieveAccountById(int accountId) { 
		AccountCache cache = AccountCache.getInstance();
		Account account = null;
		if (cache.containsId(accountId)) {
			account =  cache.getAccountById(accountId);
			return account;
		}
		
		account = repository.findByAccountId(accountId);
		cache.addAccount(account); 

		return account;
	}
	
	
	
	
}
