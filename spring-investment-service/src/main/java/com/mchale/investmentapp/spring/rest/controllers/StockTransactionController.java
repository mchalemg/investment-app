package com.mchale.investmentapp.spring.rest.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StockTransactionController {

	@PostMapping("/transactions/upload/fidelity/account/{accountId}")
	public void uploadFidelityCSV(@RequestParam("file") MultipartFile file) {
		System.err.print("hello workd"); 
		try {
			InputStream inputStream = file.getInputStream(); 
			BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
			Stream<String> stream = r.lines();
			stream.forEach(System.out::println); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
