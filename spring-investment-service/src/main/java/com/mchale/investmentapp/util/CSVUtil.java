package com.mchale.investmentapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mchale.investmentapp.rest.model.csv.DataRow;
import com.mchale.investmentapp.rest.model.csv.DataSheet;
import com.mchale.investmentapp.rest.model.csv.DataValue;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketTransaction;
import com.opencsv.CSVReader;

public class CSVUtil {

	
	public static DataSheet read(List<String[]> csv) {
		DataSheet datasheet = new DataSheet();
		String[] csvHeader = csv.get(0); 
		for(int i = 0; i < csvHeader.length; i++) {
			datasheet.addColumnName(csvHeader[i]);
		}
		
		if (csv.size() > 1) {
		//	DataValueBuilder dataValueBuilder = new DataValueBuilder(context);
			
			//iterate over the rows in the csv
			for (int i = 1; i < csv.size(); i++) {
				String[] currentRow = csv.get(i);
				DataRow datarow = new DataRow();
				datarow.setColumnNames(datasheet.getColumnNames());
				datasheet.addRow(datarow);
				
				//iterate over the columns
				for (int j = 0; j < currentRow.length; j++) {
					
					String valueText = currentRow[j]; 
					
					DataValue dataValue = new DataValue(valueText);
					
					String columnName = csvHeader[j];
					datarow.add(columnName, dataValue);
				}
				
			}
		}
		return datasheet;
	}

	
	public static List<String[]> readCSV(MultipartFile file) {
		InputStream inputStream = null;
		CSVReader csvReader = null;
		List<String[]> readAll = Collections.emptyList();
		try {
			inputStream = file.getInputStream(); 
			csvReader = new CSVReader(new InputStreamReader(inputStream));
			readAll = csvReader.readAll();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
			closeStreams(inputStream, csvReader);
		}
		return readAll;
		
	}
	public static void closeStreams(InputStream inputStream, CSVReader csvReader) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (csvReader != null)  {
			try {
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static String getColumnValue(List<String[]> csvSheet, int row, int column) {
		return csvSheet.get(row)[column];
	}


	public static int findRowWithValue(List<String[]> rows, int column, String searchValue)
	{
	    for (int i = 0; i < rows.size(); i++)
	    {
	        String[] row = rows.get(i);
	        if (row != null && row.length > column)
	        {
	            String colVal = row[column];
	            if (StringUtils.equals(colVal, searchValue))
	            {
	                return i;
	            }
	        }
	    }
	    return -1;
	}	

	
}
