package com.example.springmongodb.api.resource;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongodb.api.model.Chart;
import com.example.springmongodb.api.model.Customer;
import com.example.springmongodb.api.model.Item;
import com.example.springmongodb.api.repository.ChartRepository;
import com.example.springmongodb.api.repository.CustomerRepository;
import com.example.springmongodb.api.repository.ItemRepository;

@RestController
public class ChartController {
	@Autowired
	ChartRepository repository;
	@Autowired
	CustomerRepository custReposiroty;
	@Autowired
	ItemRepository itemRepository;
	
	@PostMapping("/insertChart")
	public String insertChart(@RequestBody Chart chart) {
		System.out.println(chart.getCustomerId());
		Optional<Customer> optionalCust = custReposiroty.findById(chart.getCustomerId());
		Optional<Item> optionalItem = itemRepository.findById(chart.getItemId());
		if(optionalCust.isPresent()) {
			Customer cust = optionalCust.get();
			Item item = optionalItem.get();
			int stock = item.getItemStock() - chart.getQuantity();
			item.setItemStock(stock);
			itemRepository.save(item);
			repository.save(chart);
			return "Chart Id : " + chart.getChartId() + ", CustomerName : " + cust.getCustomerName() + ", CustomerAddress : " +
			cust.getCustomerAddress() + ", ItemName : " + item.getItemName() + ", ItemStock" + item.getItemStock();
		}else {
			return "Can't insert chart";
		}
		
	}
	
}
