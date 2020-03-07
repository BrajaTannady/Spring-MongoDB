package com.example.springmongodb.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springmongodb.api.model.Chart;

public interface ChartRepository extends MongoRepository<Chart, String>{

}
