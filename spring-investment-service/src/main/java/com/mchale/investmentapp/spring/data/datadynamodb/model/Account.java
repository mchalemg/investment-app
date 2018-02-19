package com.mchale.investmentapp.spring.data.datadynamodb.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
 
@DynamoDBTable(tableName = "Account")
public class Account {
	private String accountId;
	private String accountName;
	private String accountNumber;
	private String accountType;
	private String institution;
	public Account() {
		super();
	}
	public Account(String accountId, String accountName, String accountNumber, String accountType, String institution) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.institution = institution;
	}
	
	
	@DynamoDBHashKey(attributeName = "accountId")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@DynamoDBAttribute(attributeName = "accountName")
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@DynamoDBAttribute(attributeName = "accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@DynamoDBAttribute(attributeName = "accountType")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@DynamoDBAttribute(attributeName = "institution")
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", institution=" + institution + "]";
	}
	
	
}
