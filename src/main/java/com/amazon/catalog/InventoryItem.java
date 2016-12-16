package com.amazon.catalog;

import java.io.IOException;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="inventoryitem")
public class InventoryItem implements Comparable<InventoryItem> {	
	
	@Id
	@GeneratedValue	
	@Column(name="inventoryitemid")
	private int inventoryItemId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
		
	@Column(name="unitprice")
	private double unitPrice;
	
	@Column(name="inventory")
	private int inventory;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name ="categoryid")
	private Category category;
	
	public InventoryItem() {		
	}
	
	public InventoryItem(String jsonURL) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InventoryItem clone = mapper.readValue(new URL(jsonURL), InventoryItem.class);
		this.inventoryItemId = clone.getInventoryItemId();
		this.name = clone.getName();
		this.description = clone.getDescription();
		this.category = clone.getCategory();
	}
	
	public InventoryItem(String name, double unitPrice, int inventory, Category category) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.inventory = inventory;
		this.category = category;
	}
	
	public int getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(int inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int compareTo(InventoryItem that) {
		return this.getName().compareTo(that.getName());
	}
}