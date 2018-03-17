package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
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
		Account acct = repository.findByAccountId(1);
		String institution = acct.getInstitution();
		assertEquals("Fidelity", institution);
	}

	@Before
	public void InsertUserAccountNameInstitution() {
		Account acct = new Account(-1, 0, "My Account", "12345", "401k", "Fidelity");
		repository.save(acct);
	}
	
	@Test
	public void testGetUserAccountNameInstitution() {
		Account acct = repository.findByUserIdAndInstitutionAndAccountName(0, "Fidelity", "My Account");
		String institution = acct.getAccountNumber();
		assertEquals("12345", institution);
	}
	

	@Test
	public void testGetAccountByAccountNum() {
		Account acct = repository.findByAccountNumber("81664");
		String institution = acct.getInstitution();
		assertEquals("Fidelity", institution);
	}

	@After
	public void deleteUserAccountNameInstitution() {
		Account acct = repository.findByAccountId(-1);
		repository.delete(acct); 
	}
	
}
