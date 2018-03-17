package com.mchale.investmentapp.spring.data.cache;

import java.util.HashMap;
import java.util.Map;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;

public class AccountCache {

	private Map<Integer, Account> accountsById;

	private static AccountCache _instance = new AccountCache();
	
	private AccountCache () {
		this.accountsById = new HashMap<>();
	}
	
	public static AccountCache getInstance() {
		return _instance;
	}
	
	public boolean containsId(Integer id) {
		return this.accountsById.containsKey(id); 
	}
	
	
	public Account getAccountById(Integer id) {
		return this.accountsById.get(id);
	}


	public void addAccount(Account account) {
		if (account == null) return;
		
		Integer id = account.getAccountId();
		if (this.containsId(id)) return;
		
		synchronized (accountsById) {
			if (!this.containsId(id)) {
				this.accountsById.put(id, account);
			}			
		}
	}
	
}
