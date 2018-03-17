package com.mchale.investmentapp.spring.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mchale.investmentapp.rest.model.AlphaVantageConnector;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.spring.rest.services.StockMarketAssetService;
import com.mchale.investmentapp.spring.rest.services.StockMarketPriceService;

@RestController
public class StockPriceController {

	
	@Autowired
	private StockMarketPriceService service;
	
	@Autowired
	private StockMarketAssetService assetService;

	
	@PostMapping ("/trigger/priceupdate/{symbol}")
	public void triggerPriceUpdate(@PathVariable String symbol) {
		AlphaVantageConnector alphaVantage = AlphaVantageConnector.getInstance();
		List<StockMarketPrice> pricesForSymbol = alphaVantage.fetchAllPricesForSymbol(symbol);
		service.savePrices(pricesForSymbol);
	}	
	
	@PostMapping ("/trigger/priceupdate/all")
	public void triggerPriceUpdateAll() {
		AlphaVantageConnector alphaVantage = AlphaVantageConnector.getInstance();
		Iterable<StockMarketAsset> allAssets = this.assetService.retrieveAllAssets(); 
		System.out.println(allAssets);
 		for (StockMarketAsset stockMarketAsset : allAssets) {
			String symbol = stockMarketAsset.getSymbol();
			System.out.println(symbol); 
			List<StockMarketPrice> pricesForSymbol = alphaVantage.fetchAllPricesForSymbol(symbol);
			service.savePrices(pricesForSymbol); 
		}
	}	
	
	
}
