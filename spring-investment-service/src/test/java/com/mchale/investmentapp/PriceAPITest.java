package com.mchale.investmentapp;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchale.investmentapp.rest.model.AlphaVantageConnector;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketTransactionRepository;
import com.mchale.investmentapp.spring.rest.services.StockMarketPriceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceAPITest {
	//https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&outputsize=full
	/*
Public Const ALPHADVANTAGE_API_KEY = "RRDB1X5FJ2W7DQH2"
Public Const ALPHADVANTAGE_HIST_URL As String = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&outputsize=full"
Public Const API_KEY_PARAM = "&apikey="
Public Const SYMBOL_PARAM_NAME = "&symbol="

	 */
	@Autowired
	private StockMarketPriceService service;
	
	@Autowired
	private StockMarketTransactionRepository txRepository;

	@Test
	public void deletetransactions () {
		txRepository.deleteAll();
	}
	
//	@Test
//	public void testMaxDateForSymbol() {
//		long maxDate = this.service.getMaxDate("VUG");
//		System.out.println("maxDate: " + maxDate); 
//		
//	}
	
//	
//	@Test
//	public void testRetrievePrices() {
//		try {			
//			AlphaVantageConnector alphaVantage = AlphaVantageConnector.getInstance();
//			List<StockMarketPrice> pricesForSymbol = alphaVantage.fetchPricesForSymbol("VUG", new DateTime().minusDays(10)); 
//			System.out.println("list size: " +  pricesForSymbol.size() ); 
//			this.service.savePrices(pricesForSymbol); 
//		} catch (Exception e) {
//		   e.printStackTrace();
//		} 
//
//	}
	
}
