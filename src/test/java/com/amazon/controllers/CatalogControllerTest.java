package com.amazon.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazon.catalog.CatalogController;
import com.amazon.catalog.Category;
import com.amazon.catalog.CategoryRepository;
import com.amazon.catalog.InventoryItem;
import com.amazon.catalog.InventoryItemRepository;

public class CatalogControllerTest {
	
	@InjectMocks
	private CatalogController catalogController;

	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private InventoryItemRepository iiRepository;
			
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}		
	
	@Test
	public void testAddCategory() {
		Category aCategory = new Category();
		aCategory.setName("Hardware");
		when(categoryRepository.save(aCategory)).thenReturn(aCategory);		
		assertThat(catalogController.addCategory(aCategory).getName(), is("Hardware"));		
	}
		
	@Test
	public void testFindCategory() {
		Category aCategory = new Category();
		aCategory.setName("Hardware");
		when(categoryRepository.findByName("Hardware")).thenReturn(aCategory);		
		assertThat(catalogController.findCategoryByName("Hardware"), isA(Category.class));		
	}
	
	@Test
	public void testFindAllCategories() {
		Category aCategory = new Category();
		aCategory.setName("Hardware");
		
		Category bCategory = new Category();
		bCategory.setName("Tools");
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(aCategory);
		categories.add(bCategory);		
		when(categoryRepository.findAll()).thenReturn(categories);		
		assertThat(catalogController.findAllCategories(), IsCollectionWithSize.hasSize(2));
	}
	
	@Test
	public void testAddInventoryItem() {
		Category hardware = new Category();
		hardware.setName("Hardware");
		
		InventoryItem ii = new InventoryItem("Hammer", 10.99, 10, hardware);
		when(iiRepository.save(ii)).thenReturn(ii);
		
		assertThat(catalogController.addInventoryItem(ii).getCategory().getName(), is("Hardware"));
		assertThat(catalogController.addInventoryItem(ii).getName(), is("Hammer"));		
		assertThat(catalogController.addInventoryItem(ii).getUnitPrice(), isA(Double.class));
	}
	
	@Test
	public void testFindInventoryItemSort() {
		Category hardware = new Category();
		hardware.setName("Hardware");
		
		InventoryItem ii1 = new InventoryItem("Screws 3inch 100Box", 10.99, 10, hardware);
		InventoryItem ii2 = new InventoryItem("Hammer", 10.99, 10, hardware);
		InventoryItem ii3 = new InventoryItem("Nails 3inch 100Box", 10.99, 10, hardware);
		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		inventoryItems.add(ii1);
		inventoryItems.add(ii2);
		inventoryItems.add(ii3);		
		when(iiRepository.findAll()).thenReturn(inventoryItems);
		
		assertThat(catalogController.findAllInventoryItems().get(0).getName(), is("Hammer"));
		assertThat(catalogController.findAllInventoryItems().get(1).getName(), is("Nails 3inch 100Box"));
		assertThat(catalogController.findAllInventoryItems().get(2).getName(), is("Screws 3inch 100Box"));
	}
}
