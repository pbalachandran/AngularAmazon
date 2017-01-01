package com.amazon.shoppingcart;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.amazon.checkout.Invoice;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="shoppingcart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue	
	@Column(name="shoppingcartid")
	private int shoppingCartId;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH, mappedBy="shoppingCart", orphanRemoval=true)
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="invoiceid")
	private Invoice invoice;
	
	@Column(name="username")
	private String username;
	
	@Column(name="total")
	private Double total;
	
	@Column(name="status")
	private String status;
	
	public ShoppingCart() {
		this.status = "active";
	}
	
	public ShoppingCart(String username) {
		this.username = username;
		this.status = "active";
	}
	
	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}