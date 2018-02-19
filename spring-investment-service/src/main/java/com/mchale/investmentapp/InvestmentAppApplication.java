package com.mchale.investmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mchale.investmentapp" })
public class InvestmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentAppApplication.class, args);
	}
}
