package com.mchale.investmentapp.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mchale.investmentapp.rest.model.AccountSummary;
import com.mchale.investmentapp.spring.rest.services.AccountAggregationService;

@RestController
public class AccountAggregationController {

	@Autowired
	private AccountAggregationService service;
	
	@GetMapping("/aggregation/user/{userId}/account/{accountId}")
	public AccountSummary getAccountSummary(@PathVariable int userId, @PathVariable int accountId) {
		AccountSummary summary = this.service.calculateSummary(userId, accountId);
		return summary;
	}
	
	
}
