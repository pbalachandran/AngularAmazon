package com.amazon.shoppingcart;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amazon.catalog.InventoryItem;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="orderitem")
public class OrderItem {	
	
	@Id
	@GeneratedValue	
	@Column(name="orderitemid")
	private int orderItemId;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "inventoryitemid")
	private InventoryItem inventoryItem;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="shoppingcartid")
	private ShoppingCart shoppingCart;
	
	public OrderItem() {
	}
	
	public OrderItem(int quantity, InventoryItem inventoryItem, ShoppingCart shoppingCart) {
		this.quantity = quantity;
		this.inventoryItem = inventoryItem;
		this.shoppingCart = shoppingCart;
	}
	
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
}