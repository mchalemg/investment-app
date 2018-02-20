package com.mchale.investmentapp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchale.investmentapp.spring.data.datadynamodb.model.User;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserRepository repository;
	
	
	@Test
	public void testRetrieveUser() {
		User user = repository.findByUserId(1); 
		assertEquals("McHale", user.getLastName()); 
		
		List<Integer> accounts = user.getAccounts(); 
		assertEquals(5, accounts.size() );
		
	}
	
}
