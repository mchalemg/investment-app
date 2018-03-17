package com.mchale.investmentapp;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.junit.Test;

import com.mchale.investmentapp.rest.model.HoldingSummary;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.mchale.investmentapp.util.DateUtil;

public class AggregationTest {
	private static final int TEST_ACCOUNT_ID = 2;
	private static final String TEST_SYMBOL_GREATERTHAN = "FOOBARGT";
	private static final String TEST_SYMBOL_BETWEEN = "FOOBARBET";

	
	@Test
	public void sumTransactions() {
		
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



		Map<String, Double> totalBySymbol1 = 
				txs
					.stream()
					.collect(
						Collectors.groupingBy(
								StockMarketTransaction::getSymbol,
								Collectors.summingDouble(StockMarketTransaction::getShares)
								));

		System.out.println(totalBySymbol1); 

		
		Map<String, List<StockMarketTransaction>> groupedTransactions = 
				txs
					.stream()
					.collect(
						Collectors.groupingBy(
								StockMarketTransaction::getSymbol));

		
		Set<String> symbolSet = groupedTransactions.keySet();
		List<HoldingSummary> summaryList = new ArrayList<>();
		
		for (String symbol : symbolSet) {
			List<StockMarketTransaction> transactions = groupedTransactions.get(symbol);

			double shares = transactions.stream().mapToDouble(StockMarketTransaction::getShares).sum();
			double fees = transactions.stream().mapToDouble(StockMarketTransaction::getFees).sum();
			double amountInvested = transactions.stream().mapToDouble(StockMarketTransaction::getAmount).sum();
			HoldingSummary holdingSummary = new HoldingSummary();
			holdingSummary.setAmountInvested(amountInvested); 
			holdingSummary.setSymbol(symbol);
			holdingSummary.setFees(fees);
			holdingSummary.setShares(shares); 
			
			summaryList.add(holdingSummary);
		}
			System.out.println("summaryList: " + summaryList);
		
	}
	
	
}
