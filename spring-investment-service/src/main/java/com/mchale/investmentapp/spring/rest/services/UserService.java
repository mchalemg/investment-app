package com.mchale.investmentapp.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.User;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.AccountRepository;
import com.mchale.investmentapp.spring.data.datadynamodb.repositories.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User retrieveUserById(int userId) { 
		return repository.findByUserId(userId);
	}
	
}
