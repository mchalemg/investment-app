package com.mchale.investmentapp.spring.data.cache;

import java.util.HashMap;
import java.util.Map;

import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;

public class StockMarketAssetCache {

	private Map<String, StockMarketAsset> assetsBySymbol;
	private Map<String, StockMarketAsset> assetsByShortName;

	private static StockMarketAssetCache _instance = new StockMarketAssetCache();
	
	private StockMarketAssetCache () {
		this.assetsBySymbol = new HashMap<>();
		this.assetsByShortName = new HashMap<>();
	}
	
	public static StockMarketAssetCache getInstance() {
		return _instance;
	}
	
	public boolean containsSymbol(String symbol) {
		return this.assetsBySymbol.containsKey(symbol); 
	}
	
	public boolean containsShortName(String shortName) {
		return this.assetsByShortName.containsKey(shortName); 
	}

	
	public StockMarketAsset getAssetBySymbol(String symbol) {
		return this.assetsBySymbol.get(symbol);
	}

	public StockMarketAsset getAssetByShortName(String shortName) {
		return this.assetsByShortName.get(shortName);
	}

	public void addStockMarketAsset(StockMarketAsset asset) {
		if (asset == null) return;
		
		String symbol = asset.getSymbol();
		String shortName = asset.getShortName();
		if (this.containsSymbol(symbol)) return;
		
		synchronized (assetsBySymbol) {
			if (!this.containsSymbol(symbol)) {
				this.assetsBySymbol.put(symbol, asset);
				this.assetsByShortName.put(shortName, asset);
			}			
		}
	}
	
}
