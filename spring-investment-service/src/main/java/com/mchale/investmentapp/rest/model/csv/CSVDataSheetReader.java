package com.mchale.investmentapp.rest.model.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;




public class CSVDataSheetReader extends DataSheetReader{

	private static Logger logger = Logger.getLogger(CSVDataSheetReader.class);
	

	@Override
	public DataSheet read() throws IOException {
		
		CSVReader csvReader = null;
		try {
			
			csvReader = new CSVReader(new FileReader(this.path));
			List<String[]> csv = csvReader.readAll(); 
			
			DataSheet datasheet = read(csv);
			
			return datasheet;
		}
		finally {
			if (csvReader != null)
				csvReader.close();
		}
	}


	private DataSheet read(List<String[]> csv) {
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
				DataRow datarow = this.dataRowFactory.createDataRow();
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

	
	

}
