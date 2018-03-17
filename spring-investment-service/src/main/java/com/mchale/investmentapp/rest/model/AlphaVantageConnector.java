package com.mchale.investmentapp.rest.model;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketPrice;
import com.mchale.investmentapp.util.DateUtil;
import com.mchale.investmentapp.util.HttpUtil;

public class AlphaVantageConnector {

	private static final String BASE_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED";
	private static final String OUTPUT_SIZE_PARAM_FULL = "&outputsize=full";
	private static final String OUTPUT_SIZE_PARAM_COMPACT = "&outputsize=compact";
	
	private static final String API_KEY_PARAM = "&apikey=";
	private static final String API_KEY = "RRDB1X5FJ2W7DQH2";
	private static final String SYMBOL_PARAM = "&symbol=";
	
	private AlphaVantageConnector () {		
	}
	
	private String fetchSymbol(String symbol, String outputsize) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		
		RestTemplate rest = HttpUtil.buildRestTemplateTrustAll();			
		System.out.println("end building rest template");
		String buildUrl = BASE_URL + outputsize + API_KEY_PARAM + API_KEY + SYMBOL_PARAM + symbol;
		System.out.println("AlphaVantage URL: " + buildUrl); 
		long startCall = System.currentTimeMillis();
		String json = rest.getForObject(buildUrl, String.class);
		long endCall = System.currentTimeMillis();
		System.out.println("end call"); 
		System.out.println("elapsed time (s): " + ((endCall - startCall)/1000)); 
	
		
		return json;
	}
	 
	public List<StockMarketPrice> fetchAllPricesForSymbol(String symbol) { 
		return fetchPricesForSymbol(symbol, DateUtil.epoch()); 
	}

	
	public List<StockMarketPrice> fetchPricesForSymbol(String symbol, DateTime greaterThan) { 
		List<StockMarketPrice> datePrices = new ArrayList<>();
		
		try {
			String outputsize = OUTPUT_SIZE_PARAM_FULL;
			if (greaterThan.isAfter(new DateTime().minusDays(100))) {
				outputsize = OUTPUT_SIZE_PARAM_COMPACT;
			}
			String json = fetchSymbol(symbol,outputsize); 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(json);
			JsonNode timeSeriesNode = jsonNode.get("Time Series (Daily)");
			Iterator<String> fieldNames = timeSeriesNode.fieldNames();
			while (fieldNames.hasNext()) {
				String nextDate = fieldNames.next();
				JsonNode jsonDate = timeSeriesNode.get(nextDate);
				DateTime dtNextDate = DateUtil.getDateFromYearMonthDay(nextDate);
				if (dtNextDate.isAfter(greaterThan)) {
					StockMarketPrice datePrice = buildDatePrice(dtNextDate, jsonDate, symbol);
					datePrices.add(datePrice); 
				}
			}
						
		} catch (KeyManagementException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datePrices;
	}
	
	
	public static StockMarketPrice buildDatePrice(DateTime date, JsonNode node, String symbol) {
//		DatePrice price = new DatePrice();
		StockMarketPrice price = new StockMarketPrice();
		price.setDateSymbol(DateUtil.formatYearMonthDay(date) + "_" + symbol);  
		price.setSymbol(symbol); 
		price.setStatDateMilis(date.getMillis()); 
		price.setClosePrice(getClose(node));  
		price.setAdjustedClosePrice(getAdjustedClose(node)); 
		price.setLowPrice(getLow(node));
		price.setHighPrice(getHigh(node));
		price.setDividend(getDividendAmount(node)); 
		price.setSplitCoefficient(getSpiltCoefficient(node)); 
		price.setVolume(getVolume(node));  
		return price; 
	}

	private static Double getDividendAmount(JsonNode node) { 
		return getDouble(node, "7. dividend amount");
	}

	private static Double getSpiltCoefficient(JsonNode node) { 
		return getDouble(node, "8. split coefficient");
	}
	
	
	private static Double getVolume(JsonNode node) { 
		return getDouble(node, "6. volume");
	}

	private static Double getClose(JsonNode node) { 
		return getDouble(node, "4. close");
	}

	private static Double getAdjustedClose(JsonNode node) { 
		return getDouble(node, "5. adjusted close");
	}
	
	private static Double getLow(JsonNode node) { 
		return getDouble(node, "3. low");
	}
	
	private static Double getOpen(JsonNode node) { 
		return getDouble(node, "1. open");
	}
	
	private static Double getHigh(JsonNode node) { 
		return getDouble(node, "2. high");
	}

	
	private static Double getDouble(JsonNode node, String attributeName) {
		JsonNode doubleNode = node.get(attributeName);
		if (doubleNode == null)
			return null;
		
		double doubleValue = doubleNode.asDouble();
		return doubleValue;
	}
	
	
	public static AlphaVantageConnector getInstance() {
		return new AlphaVantageConnector();
	}
	
}
