package com.mchale.investmentapp.rest.model.csv;


import java.io.IOException;



public abstract class DataSheetReader {
	protected DataRowFactory dataRowFactory;
	protected String path;

	public abstract DataSheet read() throws IOException; 
	
	
	
	
}
