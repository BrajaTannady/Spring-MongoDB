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

import com.example.springmongodb.api.model.Item;
import com.example.springmongodb.api.repository.ItemRepository;

@RestController
public class ItemController {
	@Autowired
	ItemRepository repository;
	
	@PostMapping("/insertItem")
	public String insertData(@RequestBody Item item) {
		repository.save(item);
		return "Inserted Data : " + item.getItemId();
	}
	
	@GetMapping("/findAllItem")
	public List<Item> findData() {
		return repository.findAll();
	}
	
	@GetMapping("/findItem/{id}")
	public String findDataById(@PathVariable String id) {
		Optional<Item> optional;
		optional = repository.findById(id);
		
		if(optional.isPresent()) {
			Item item = optional.get();
			return "Item id : " + item.getItemId() + ", Item Name : " + item.getItemName() + ", Item Stock : " + item.getItemStock()
			+ ", Item Desc : " + item.getItemDescription();
		}else {
			return "Can't find item";
		}
	}
	
	@PutMapping("/updateItem/{id}")
	public String updateItemById(@PathVariable String id, @RequestBody Item item) {
		Optional<Item> optional;
		optional = repository.findById(id);
		if (optional.isPresent()) {
			Item temp = optional.get();
			item.setItemId(temp.getItemId());
			item.setItemName(temp.getItemName());
			repository.save(item);
			return "Data have been updated : " + item.getItemId() + ", Stock : " + item.getItemStock();
		}else {
			return "Can't find item";
		}
	}
	
	@DeleteMapping("/deleteItem/{id}")
	public String deleteItemById(@PathVariable String id) {
		Optional<Item> optional;
		optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return "Item deleted : " + id;
		}else {
			return "Can't find item";
		}
	}
}
