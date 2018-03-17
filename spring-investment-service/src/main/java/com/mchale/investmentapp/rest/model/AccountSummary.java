package com.mchale.investmentapp.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;

public class AccountSummary {

	private Account account;
	
	private double amountInvested;
	private double fees;
	
	private List<HoldingSummary> holdingSummaries;
	
	public AccountSummary() {
		this.setHoldingSummaries(new ArrayList<>());
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public List<HoldingSummary> getHoldingSummaries() {
		return holdingSummaries;
	}

	public void setHoldingSummaries(List<HoldingSummary> holdingSummaries) {
		this.holdingSummaries = holdingSummaries;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void addAmountInvested(double amount) {
		this.amountInvested = this.amountInvested + amount;
	}
	
	public void addFees(double fees) {
		this.fees = this.fees + fees;
	}


}
