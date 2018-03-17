package com.mchale.investmentapp.spring.data.datadynamodb.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;



@EnableScan
public interface StockMarketPriceRepository extends CrudRepository<StockMarketPrice, String> {

	public List<StockMarketPrice> findBySymbol(String symbol);
//	public long findMaxsStatDateMilisBySymbol(String symbol);

}
