package com.example.springmongodb.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springmongodb.api.model.Item;

public interface ItemRepository extends MongoRepository<Item, String>{

}
