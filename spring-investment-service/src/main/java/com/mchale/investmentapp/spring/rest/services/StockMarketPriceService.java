package com.mchale.investmentapp.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketPriceRepository;

@Component
public class StockMarketPriceService {

	@Autowired
	private StockMarketPriceRepository repository;
	
	public void savePrices(List<StockMarketPrice> prices) {
		repository.save(prices); 
	}
	
	public long getMaxDate(String symbol) {
		
		return 0L; //this.repository.findMaxsStatDateMilisBySymbol(symbol);
		
	}
	
}
