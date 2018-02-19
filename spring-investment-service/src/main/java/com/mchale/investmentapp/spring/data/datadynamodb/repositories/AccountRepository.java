package com.mchale.investmentapp.spring.data.datadynamodb.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;



@EnableScan
public interface AccountRepository extends CrudRepository<com.mchale.investmentapp.spring.data.datadynamodb.model.Account, String> {

	public Account findByAccountId(String accountId);
	public Account findByAccountNumber(String accountNumber);
}
