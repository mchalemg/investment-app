package com.mchale.investmentapp.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketTransactionRepository;

@RestController
public class StockMarketTransactionService {

	@Autowired
	private StockMarketTransactionRepository repository;
	
	public void insertTransactions(List<StockMarketTransaction> transactions) {
		repository.save(transactions); 
	}
	
	
	
	
}
