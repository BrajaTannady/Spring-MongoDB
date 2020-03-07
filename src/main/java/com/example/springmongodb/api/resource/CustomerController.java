package com.example.springmongodb.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongodb.api.model.Customer;
import com.example.springmongodb.api.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository repository;
	
	@PostMapping("/insertData")
	public String insertData(@RequestBody Customer cust) {
		repository.save(cust);
		return "Data added : " + cust.getCustomerId();
	}
	
	@GetMapping("/findAllData")
	public List<Customer> findAllData(){
		return repository.findAll();
	}
	
	@GetMapping("/findData/{id}")
	public String findDatabyId(@PathVariable String id) {
		Optional<Customer> optional;
		optional = repository.findById(id);
		if(optional.isPresent()) {
			Customer cust = optional.get();
			return "Customer Id : " + cust.getCustomerId() + "Customer Name : " + cust.getCustomerName() 
			+ "Customer Address : " + cust.getCustomerAddress();
		}else {
			return "Can't find data";
		}
	}
	
	@PutMapping("/updateData/{id}")
	public String updateDataById(@PathVariable String id, @RequestBody Customer cust) {
		Optional<Customer> optional;
		optional = repository.findById(id);
		if (optional.isPresent()) {
			Customer temp = optional.get();
			cust.setCustomerId(temp.getCustomerId());
			repository.save(cust);
			return "Data updated : " + id;
		}else {
			return "Can't find data";
		}
	}
	
	@DeleteMapping("/deleteData/{id}")
	public String deleteDataById(@PathVariable String id) {
		Optional<Customer> optional;
		optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return "Deleted data : " + id;
		}else {
			return "Can't find data";
		}
	}
}
