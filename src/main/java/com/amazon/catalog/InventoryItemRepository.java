package com.amazon.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {	
	public InventoryItem findByInventoryItemId(int inventoryItemId);
	public InventoryItem findByName(String name);	
}