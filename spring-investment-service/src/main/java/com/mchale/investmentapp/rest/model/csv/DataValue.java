package com.mchale.investmentapp.rest.model.csv;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class DataValue {
	

	private String rawText;
 	private DateTime date;
	private String string;
	private DataType dataType;
	private List<DataRow> dataRows;
	public DataValue() {}
	
	public DataValue(String valueText) {
		this.setRawText(valueText);
		this.setString(valueText);
		this.setDataType(DataType.STRING); 						
	}
	
	public DataValue copy() { 
		DataValue value = new DataValue();
		value.rawText = rawText;
		if (this.date != null)
			value.date = new DateTime(this.date.getMillis());
		value.dataType = this.dataType;
		if (dataRows != null) {
			List<DataRow> dataRowsCopy = new ArrayList<DataRow>();
			for (int i = 0; i < this.dataRows.size(); i++) {
				dataRowsCopy.add(this.dataRows.get(i)); 
			}
			value.setDataRows(dataRowsCopy);
		}
		return value;
	}
	
	public String getRawText() {
		return rawText;
	}

	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}




	@Override
	public String toString() {
		return "DataValue [rawText=" + rawText + ", date=" + date + ", string="
				+ string + ", dataType=" + dataType + ", dataRows=" + dataRows
				+ "]";
	}

	public List<DataRow> getDataRows() {
		return dataRows;
	}

	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}


	
	
}
