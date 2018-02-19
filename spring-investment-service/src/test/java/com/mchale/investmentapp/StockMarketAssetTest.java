package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockMarketAssetTest {

	@Autowired
	private StockMarketAssetRepository repository;

	@Test
	public void testASaveAsset() {
		StockMarketAsset asset = new StockMarketAsset("FOOBAR2", "TEST ASSET", "ETF", "FAMILY2", "S&P 500");
		repository.save(asset);
		StockMarketAsset asset2 = repository.findBySymbol("FOOBAR2");
		assertEquals(asset.getFundFamily(), asset2.getFundFamily());		
	}	
	
	@Test
	public void testCGetAsset() {
		StockMarketAsset asset2 = repository.findBySymbol("SPY");
		assertEquals("SPDR", asset2.getFundFamily());		
	}	

	
	@Test
	public void testBDeleteAsset() {		
		repository.delete("FOOBAR2");
		StockMarketAsset asset = repository.findBySymbol("FOOBAR2");
		assertNull(asset);		
	}	

}
