package com.mchale.investmentapp.spring.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.spring.data.cache.StockMarketAssetCache;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;

@Component
public class StockMarketAssetService {

	@Autowired
	private StockMarketAssetRepository repository;
	
	
	public StockMarketAsset retrieveAssetBySymbol(String symbol) {
		StockMarketAssetCache cache = StockMarketAssetCache.getInstance();
		StockMarketAsset asset = null;
		if (cache.containsSymbol(symbol)) {
			asset =  cache.getAssetBySymbol(symbol);
			return asset;
		}
		
		asset = repository.findBySymbol(symbol);
		cache.addStockMarketAsset(asset); 
		return asset;
		
	}
	
	public StockMarketAsset retrieveAssetByShortName(String shortName) {
		StockMarketAssetCache cache = StockMarketAssetCache.getInstance();
		StockMarketAsset asset = null;
		if (cache.containsShortName(shortName)) {
			asset =  cache.getAssetByShortName(shortName);
			return asset;
		}
		
		asset = repository.findByShortName(shortName);
		cache.addStockMarketAsset(asset); 
		return asset;
		
	}
	
	
	public Iterable<StockMarketAsset> retrieveAllAssets() {
		Iterable<StockMarketAsset> findAll = this.repository.findAll();
		return findAll;
	}


}
