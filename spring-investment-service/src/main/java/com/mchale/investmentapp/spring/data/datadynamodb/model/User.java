package com.mchale.investmentapp.spring.data.datadynamodb.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "User")
public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private List<Integer> accounts;
	
	@DynamoDBHashKey(attributeName = "userId")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@DynamoDBAttribute(attributeName = "firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@DynamoDBAttribute(attributeName = "lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@DynamoDBAttribute(attributeName = "accounts")
	public List<Integer> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Integer> accounts) {
		this.accounts = accounts;
	}
	
	
	
	
}
