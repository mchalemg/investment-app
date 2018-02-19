package com.mchale.investmentapp.spring.data.datadynamodb.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "StockMarketPrice")
public class StockMarketPrice {
	
	private String dateSymbol;
	private Date statDate;
	private String symbol;
	private Double openPrice;
	private Double closePrice;
	private Double highPrice;
	private Double lowPrice;
	private Double adjustedClosePrice;
	private Double volume;

	public StockMarketPrice() {
		super();
	}
	
	
	public StockMarketPrice(String dateSymbol, Date statDate, String symbol, Double openPrice, Double closePrice,
			Double highPrice, Double lowPrice, Double adjustedClosePrice, Double volume) {
		super();
		this.dateSymbol = dateSymbol;
		this.statDate = statDate;
		this.symbol = symbol;
		this.openPrice = openPrice;
		this.closePrice = closePrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.adjustedClosePrice = adjustedClosePrice;
		this.volume = volume;
	}
		
	
	@DynamoDBHashKey(attributeName = "date_symbol")
	public String getDateSymbol() {
		return dateSymbol;
	}
	public void setDateSymbol(String dateSymbol) {
		this.dateSymbol = dateSymbol;
	}
	
	@DynamoDBAttribute(attributeName = "statDate")
	public Date getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	
	@DynamoDBAttribute(attributeName = "symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@DynamoDBAttribute(attributeName = "openPrice")
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	
	@DynamoDBAttribute(attributeName = "closePrice")
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	
	@DynamoDBAttribute(attributeName = "highPrice")
	public Double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	
	@DynamoDBAttribute(attributeName = "lowPrice")
	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	
	@DynamoDBAttribute(attributeName = "adjustedClosePrice")
	public Double getAdjustedClosePrice() {
		return adjustedClosePrice;
	}
	public void setAdjustedClosePrice(Double adjustedClosePrice) {
		this.adjustedClosePrice = adjustedClosePrice;
	}
	
	@DynamoDBAttribute(attributeName = "volume")
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	
}
