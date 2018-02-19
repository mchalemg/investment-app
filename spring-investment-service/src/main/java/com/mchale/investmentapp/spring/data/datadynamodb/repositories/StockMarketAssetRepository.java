package com.mchale.investmentapp.spring.data.datadynamodb.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;



@EnableScan
public interface StockMarketAssetRepository extends CrudRepository<StockMarketAsset, String> {

	public StockMarketAsset findBySymbol(String symbol);
	public StockMarketAsset findByShortName(String shortName);
}
