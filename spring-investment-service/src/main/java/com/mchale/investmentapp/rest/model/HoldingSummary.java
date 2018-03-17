package com.mchale.investmentapp.rest.model;

import java.util.function.Consumer;

import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;

public class HoldingSummary implements Consumer<StockMarketTransaction>{

	private StockMarketAsset asset; 
	private String symbol;
	private Double shares;
	private Double amountInvested;
	private Double fees;
	
	
	
	public HoldingSummary(String symbol, Double shares, Double amountInvested) {
		super();
		this.symbol = symbol;
		this.shares = shares;
		this.amountInvested = amountInvested;
	}



	
	public HoldingSummary() {
		super();
		this.symbol = "";
		this.shares = 0.0;
		this.amountInvested = 0.0;
		this.fees = 0.0;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getShares() {
		return shares;
	}
	public void setShares(Double shares) {
		this.shares = shares;
	}
	public Double getAmountInvested() {
		return amountInvested;
	}
	public void setAmountInvested(Double amountInvested) {
		this.amountInvested = amountInvested;
	}
	@Override
	public String toString() {
		return "HoldingSummary [symbol=" + symbol + ", shares=" + shares + ", amountInvested=" + amountInvested
				+ ", fees=" + fees + "]";
	}
	@Override
	public void accept(StockMarketTransaction t) {
		this.symbol = t.getSymbol();
		
		if (t.getAmount() != null && t.getAmount() != 0)
			this.amountInvested = t.getAmount();
		
		if (t.getFees() != null && t.getFees() != 0)
			this.fees = this.fees + t.getFees();
		
		if (t.getShares() != null && t.getShares() !=0)
			this.shares = this.shares + t.getShares();
	}
	
	public void combine(StockMarketTransaction other) {
		this.amountInvested = this.amountInvested + other.getAmount();
		this.shares = this.shares + other.getShares();
		this.fees = this.fees + other.getFees();
	}
	
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}




	public StockMarketAsset getAsset() {
		return asset;
	}




	public void setAsset(StockMarketAsset asset) {
		this.asset = asset;
	}
	
	
}
