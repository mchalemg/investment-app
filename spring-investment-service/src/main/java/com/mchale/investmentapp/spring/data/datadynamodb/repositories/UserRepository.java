package com.mchale.investmentapp.spring.data.datadynamodb.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mchale.investmentapp.spring.data.datadynamodb.model.Account;
import com.mchale.investmentapp.spring.data.datadynamodb.model.User;



@EnableScan
public interface UserRepository extends CrudRepository<com.mchale.investmentapp.spring.data.datadynamodb.model.User, String> {

	public User findByUserId(int userId);
}
