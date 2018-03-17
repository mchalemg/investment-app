package com.mchale.investmentapp.rest.model.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSheet {

	private List<String> columnNames;
	private List<DataRow> spreadsheet;
	
	public DataSheet() {
		this.columnNames = new ArrayList<String>();
		this.spreadsheet = new ArrayList<DataRow>();
	}
	


	public List<DataRow> getSpreadsheet() {
		return spreadsheet;
	}



	public List<String> getColumnNames() {
		return columnNames;
	}

	public void addColumnName(String columnName) {
		this.columnNames.add(columnName);
	}
	public void addRow(DataRow row) {
		int size = this.spreadsheet.size(); 
		row.setSheetIndex(size); 
		this.spreadsheet.add(row); 
	}
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public int getColumnSize() {
		return this.getColumnNames().size();
	}
	
	public int getRowSize() {
		return this.spreadsheet.size();
	}
}
