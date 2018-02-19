package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTests {

	@Autowired
	private AccountRepository repository;
	
	@Test
	public void testGetAccountById() {
		Account acct = repository.findByAccountId("401k-18F");
		String institution = acct.getInstitution();
		assertEquals("Fidelity", institution);
	}

	@Test
	public void testGetAccountByAccountNum() {
		Account acct = repository.findByAccountNumber("81664");
		String institution = acct.getInstitution();
		assertEquals("Fidelity", institution);
	}


}
