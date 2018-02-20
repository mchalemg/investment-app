package com.mchale.investmentapp.spring.data.datadynamodb.repositories;

import java.util.Date;
import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;



@EnableScan
public interface StockMarketTransactionRepository extends CrudRepository<StockMarketTransaction, String> {

	public List<StockMarketTransaction> findBySymbol(String symbol);
	public List<StockMarketTransaction> findByAccountId(int accountId);
	public List<StockMarketTransaction> findByAccountIdAndTxDateBetween(int accountId, long minTxDate, long maxTxDate);
	public List<StockMarketTransaction> findByAccountIdAndTxDateGreaterThan(int accountId, long txDate);
	public List<StockMarketTransaction> findByTxDate(long txDate);
	
}
