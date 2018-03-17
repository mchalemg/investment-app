package com.mchale.investmentapp.rest.model.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;


public class DataRow {
	private Integer sheetIndex;
	private List<String> columnNames;
	private Map<String, DataValue> columns;

	public DataRow() {
		this.columnNames = new ArrayList<String>();
		this.columns = new HashMap<String, DataValue>();
	}
	
	
	
	public DataRow copy() {
		DataRow row = new DataRow();
		copyColumnNames(row); 
		copyColumnValues(row);

		
		return row;
	}

	private void copyColumnValues(DataRow row) { 
		Set<String> keySet = this.columns.keySet(); 
		Iterator<String> keyIterator = keySet.iterator(); 
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			DataValue dataValue = columns.get(key); 
			DataValue valueCopy = dataValue.copy();
			row.add(key, valueCopy);	
		}
	}

	private void copyColumnNames(DataRow row) {
		row.columnNames = new ArrayList<String>();
		for (int i = 0; i < this.columnNames.size(); i++) {
			String columnName = this.columnNames.get(i);
			row.columnNames.add(new String(columnName)); 
		}
	}
	
	public Map<String, DataValue> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, DataValue> columns) {
		this.columns = columns;
	}
	
	public List<String> getColumnsNames() {
		return this.columnNames;
	}
	
	public void add(String columnName, DataValue value) {
		this.columns.put(columnName, value);
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public DataValue getDataValue(String columnName) {
		return this.columns.get(columnName);
	}
	
	public String getStringValue(String columnName) {
		DataValue dataValue = this.getDataValue(columnName); 
		return dataValue != null ? dataValue.getString() : null;
	}

	public DateTime getDate(String columnName) {
		DataValue dataValue = this.getDataValue(columnName);
		if (dataValue == null)
			return null;
		
		DateTime date = dataValue.getDate(); 
		return date;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("DataRow [");
		for ( String columnName : this.columnNames) {
			sb.append(columnName);
			sb.append(": ");
			DataValue dataValue = this.columns.get(columnName); 
			if (dataValue != null) {
				sb.append(dataValue.getRawText()); 
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}



	public Integer getSheetIndex() {
		return sheetIndex;
	}



	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	
	
}
