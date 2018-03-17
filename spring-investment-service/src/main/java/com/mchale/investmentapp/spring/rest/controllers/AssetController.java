package com.mchale.investmentapp.spring.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mchale.investmentapp.rest.model.csv.DataRow;
import com.mchale.investmentapp.rest.model.csv.DataSheet;
import com.mchale.investmentapp.spring.data.datadynamodb.model.StockMarketAsset;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.StockMarketAssetRepository;
import com.mchale.investmentapp.util.CSVUtil;

@RestController
public class AssetController {

	@Autowired
	private StockMarketAssetRepository repository;
	
	@PostMapping("assets/import")
	public List<StockMarketAsset> importAssets(@RequestParam("file") MultipartFile file) {
		List<StockMarketAsset> assets = new ArrayList<>();
		List<String[]> assetCsv = CSVUtil.readCSV(file); 
		DataSheet sheet = CSVUtil.read(assetCsv); 
		List<DataRow> rows = sheet.getSpreadsheet(); 
		for (DataRow row : rows) {
			StockMarketAsset asset = new StockMarketAsset();
			asset.setSymbol(row.getStringValue("Symbol")); 
			asset.setShortName(row.getStringValue("Identifier"));  
			asset.setName(row.getStringValue("Name"));  
			asset.setType(row.getStringValue("Type"));  
			asset.setFundFamily(row.getStringValue("FundFamilyName")); 
			assets.add(asset);
		}
		Iterable<StockMarketAsset> savedAssets = repository.save(assets); 
		return assets;
	}
	
}
