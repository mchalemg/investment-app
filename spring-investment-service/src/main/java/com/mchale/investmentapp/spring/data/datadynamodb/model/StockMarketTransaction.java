package com.mchale.investmentapp.spring.data.datadynamodb.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "StockMarketTransaction")
public class StockMarketTransaction {

	private String id;
	private long txDate; 
	private Date txDateISO; 
	private String symbol;
	private String accountId;
	private Double price;
	private Double shares;
	private String action;
	private Double amount;
	private Double fees;
	private String type;
	private String actionRaw;
	


	public StockMarketTransaction() {
		super();
	}

	public StockMarketTransaction(String id, long txDate, Date txDateISO, String symbol, String accountId, Double price, Double shares,
			String action, Double amount, Double fees, String type, String actionRaw) {
		super();
		this.id = id;
		this.txDate = txDate;
		this.symbol = symbol;
		this.accountId = accountId;
		this.price = price;
		this.shares = shares;
		this.action = action;
		this.amount = amount;
		this.fees = fees;
		this.type = type;
		this.actionRaw = actionRaw;
		this.txDateISO= txDateISO;
	}

	public StockMarketTransaction(long txDate, Date txDateISO, String symbol, String accountId, Double price, Double shares,
			String action, Double amount, Double fees, String type, String actionRaw) {
		super();
		//this.id = id;
		this.txDate = txDate;
		this.symbol = symbol;
		this.accountId = accountId;
		this.price = price;
		this.shares = shares;
		this.action = action;
		this.amount = amount;
		this.fees = fees;
		this.type = type;
		this.actionRaw = actionRaw;
	}

	
	
	@DynamoDBAttribute(attributeName = "txDateISO")
	public Date getTxDateISO() {
		return txDateISO;
	}

	public void setTxDateISO(Date txDateISO) {
		this.txDateISO = txDateISO;
	}

	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName = "actionRaw")
	public String getActionRaw() {
		return actionRaw;
	}

	public void setActionRaw(String actionRaw) {
		this.actionRaw = actionRaw;
	}

	
	@DynamoDBAttribute(attributeName = "txDate")
	public long getTxDate() {
		return txDate;
	}
	public void setTxDate(long txDate) {
		this.txDate = txDate;
	}
	
	@DynamoDBAttribute(attributeName = "symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@DynamoDBAttribute(attributeName = "accountId")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@DynamoDBAttribute(attributeName = "price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@DynamoDBAttribute(attributeName = "shares")
	public Double getShares() {
		return shares;
	}
	public void setShares(Double shares) {
		this.shares = shares;
	}
	
	@DynamoDBAttribute(attributeName = "action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@DynamoDBAttribute(attributeName = "amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@DynamoDBAttribute(attributeName = "fees")
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	
	@DynamoDBAttribute(attributeName = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
