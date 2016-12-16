package com.amazon.catalog;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/browse")
public class CatalogController {
	
	@Autowired
	private CategoryRepository categoryRepository = null;
	
	@Autowired
	private InventoryItemRepository iiRepository = null;
	
	@RequestMapping(method = RequestMethod.POST, value = "/category")
	public Category addCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category/{categoryName}")
	public Category findCategoryByName(@PathVariable String categoryName) {
		return categoryRepository.findByName(categoryName);
	}	

	@RequestMapping(method = RequestMethod.GET, value = "/categories")
	public List<Category> findAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		Collections.sort(categories);
		return categories;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/inventoryitem")
	public InventoryItem addInventoryItem(@RequestBody InventoryItem inventoryItem) {
		return iiRepository.save(inventoryItem);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/inventoryitem/{inventoryItemName}")
	public InventoryItem findInventoryItemByName(@PathVariable String inventoryItemName) {
		return iiRepository.findByName(inventoryItemName);
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/inventoryitems")
	public List<InventoryItem> findAllInventoryItems() {
		List<InventoryItem> inventoryItems = iiRepository.findAll();
		Collections.sort(inventoryItems);
		return inventoryItems;
	}	
}