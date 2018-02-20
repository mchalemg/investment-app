package com.mchale.investmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mchale.investmentapp" })
@EnableWebMvc
public class InvestmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentAppApplication.class, args);
	}
}
