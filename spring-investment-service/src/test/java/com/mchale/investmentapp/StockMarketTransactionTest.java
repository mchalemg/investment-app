package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketPriceRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketTransactionRepository;
import com.mchale.investmentapp.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockMarketTransactionTest {

	private static final int TEST_ACCOUNT_ID = 2;
	private static final String TEST_SYMBOL_GREATERTHAN = "FOOBARGT";
	private static final String TEST_SYMBOL_BETWEEN = "FOOBARBET";
	
	@Autowired
	private StockMarketTransactionRepository  repository;
	

	@Test
	public void testATestTransactions() {
		
		List<StockMarketTransaction> txs = new ArrayList<>();
	
		//account1 with date far in future for greater than
		//account 1 with earlier date for between
		
		DateTime earliestDate = DateUtil.getDateFromYearMonthDay("1990-02-01");
		DateTime latestDate = DateUtil.getDateFromYearMonthDay("1990-03-01");
		
		StockMarketTransaction stockMarketTx 
			= new StockMarketTransaction(earliestDate.getMillis(),
					earliestDate.toDate(),TEST_SYMBOL_BETWEEN, 
					TEST_ACCOUNT_ID, 
					new Double(10),new Double(2),"buy",new Double(20), 
					new Double(3), "stock", "buying");
		
		txs.add(stockMarketTx); 
		
		stockMarketTx 
		= new StockMarketTransaction(latestDate.getMillis(),
				latestDate.toDate(),TEST_SYMBOL_GREATERTHAN, 
				TEST_ACCOUNT_ID, 
				new Double(10),new Double(2),"buy",new Double(20), 
				new Double(3), "stock", "buying");

		txs.add(stockMarketTx);
		Iterable<StockMarketTransaction> save = repository.save(txs); 
		
	}
		
	@Test
	public void testBFindBetweenDates() {

		DateTime min = DateUtil.getDateFromYearMonthDay("1989-12-31");
		DateTime max = DateUtil.getDateFromYearMonthDay("1990-02-02");
		
		List<StockMarketTransaction> fetchTxs = 
				repository.findByAccountIdAndTxDateBetween(TEST_ACCOUNT_ID, 
						min.getMillis(), max.getMillis());

		assertEquals(1, fetchTxs.size());
		StockMarketTransaction stockMarketTransaction = fetchTxs.get(0);
		assertEquals(TEST_SYMBOL_BETWEEN, stockMarketTransaction.getSymbol());
	}	
	
	@Test
	public void testCFindGreaterThan() {

		DateTime min = DateUtil.getDateFromYearMonthDay("1990-02-14");
		
		List<StockMarketTransaction> fetchTxs = 
				repository.findByAccountIdAndTxDateGreaterThan(TEST_ACCOUNT_ID, 
						min.getMillis());

		assertEquals(1, fetchTxs.size());
		StockMarketTransaction stockMarketTransaction = fetchTxs.get(0);
		assertEquals(TEST_SYMBOL_GREATERTHAN, stockMarketTransaction.getSymbol());
	}	

	
	//findBySymbolAndTxDateBetween
	@Test
	public void testDDeleteAsset() {		

		List<StockMarketTransaction> prices = repository.findByAccountId(TEST_ACCOUNT_ID);
		repository.delete(prices);
		List<StockMarketTransaction> prices2 = repository.findByAccountId(TEST_ACCOUNT_ID);
		assertEquals(0, prices2.size()); 		
	}	
	
}
