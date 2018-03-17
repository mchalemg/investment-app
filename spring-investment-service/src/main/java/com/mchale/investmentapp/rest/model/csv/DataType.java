package com.mchale.investmentapp.rest.model.csv;

public class DataType {

	public static final DataType STRING = new DataType("String");
	public static final DataType DATE_TIME = new DataType("DateTime"); 
	public static final DataType INTEGER = new DataType("Integer"); 
	public static final DataType DATAROW_LIST = new DataType("DataRowList");
	
	private String name;
	
	private DataType(String name) {
		this.name = name;
	}
}
