package com.mchale.investmentapp.spring.data.datadynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "StockMarketAsset")
public class StockMarketAsset {

	private String symbol;
	private String name;
	private String type;
	private String fundFamily;
	private String shortName;
	
	
	public StockMarketAsset() {
		super();
	}
	public StockMarketAsset(String symbol, String name, String type, String fundFamily, String shortName) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.type = type;
		this.fundFamily = fundFamily;
		this.shortName = shortName;
	}
	
	@DynamoDBHashKey(attributeName = "symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@DynamoDBAttribute(attributeName = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@DynamoDBAttribute(attributeName = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@DynamoDBAttribute(attributeName = "fundFamily")
	public String getFundFamily() {
		return fundFamily;
	}
	
	public void setFundFamily(String fundFamily) {
		this.fundFamily = fundFamily;
	}
	
	@DynamoDBAttribute(attributeName = "shortName")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
}
