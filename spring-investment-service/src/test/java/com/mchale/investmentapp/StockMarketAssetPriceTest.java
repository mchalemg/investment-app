package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketPriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockMarketAssetPriceTest {

	@Autowired
	private StockMarketPriceRepository  repository;

	@Test
	public void testASaveList() {
		List<StockMarketPrice> prices = new ArrayList<>();
		DateTime dateTime = new DateTime();
		StockMarketPrice stockMarketPrice = new StockMarketPrice("2018-01-01_SPY", dateTime.getMillis(), "FOOBAR", new Double(9.99), new Double(10.99), 
				new Double(11.99), new Double(8.89), new Double(10.99), new Double(5));
		prices.add(stockMarketPrice);
		dateTime.plusDays(1);
		prices.add(new StockMarketPrice("2018-01-02_SPY", dateTime.getMillis(), "FOOBAR", new Double(10.99), new Double(11.99), 
				new Double(12.99), new Double(9.89), new Double(11.99), new Double(6)));

		repository.save(prices);
		List<StockMarketPrice> fetchPrices = repository.findBySymbol("FOOBAR");
		assertEquals(2, fetchPrices.size());
		
	}	
	/*
	@Test
	public void testBDeleteAsset() {		

		List<StockMarketPrice> prices = repository.findBySymbol("FOOBAR");
		repository.delete(prices);
		List<StockMarketPrice> prices2 = repository.findBySymbol("FOOBAR");
		assertEquals(0, prices2.size()); 		
	}	
*/
}
