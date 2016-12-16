package com.amazon.catalog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.param.CollectionFilterKeyParameterSpecification;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="category")
public class Category implements Comparable<Category> {
	
	@Id
	@GeneratedValue
	@Column(name = "categoryid")
	private int categoryId;
	
	@Column(name = "name")
	private String name;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="category")
	private Set<InventoryItem> inventoryItems = new HashSet<InventoryItem>();

	public Category() {
	}
	
	public Category(String jsonURL) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category clone = mapper.readValue(new URL(jsonURL), Category.class);
		this.categoryId = clone.getCategoryId();
		this.name = clone.getName();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<InventoryItem> getInventoryItems() {
		List<InventoryItem> l = new ArrayList<InventoryItem>(inventoryItems);
		Collections.sort(l);
		return new HashSet<>(l);
	}

	public void setInventoryItems(Set<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	@Override
	public int compareTo(Category that) {
		return this.getName().compareTo(that.getName());
	}
}