package com.example.springmongodb.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springmongodb.api.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	
}
