package com.amazon.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazon.catalog.Category;
import com.amazon.catalog.InventoryItem;
import com.amazon.catalog.InventoryItemRepository;
import com.amazon.shoppingcart.OrderItem;
import com.amazon.shoppingcart.OrderItemRepository;
import com.amazon.shoppingcart.ShoppingCart;
import com.amazon.shoppingcart.ShoppingCartController;
import com.amazon.shoppingcart.ShoppingCartRepository;


public class ShoppingCartControllerTest {

	@InjectMocks
	private ShoppingCartController shoppingCartController;
	
	@Mock
	private ShoppingCartRepository shoppingCartRepository;
	
	@Mock
	private OrderItemRepository orderItemRepository;
	
	@Mock
	private InventoryItemRepository inventoryItemRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void testDeleteShoppingCart() {
		ShoppingCart sc = new ShoppingCart();
		sc.setShoppingCartId(123);
		when(shoppingCartRepository.findByshoppingCartId(123)).thenReturn(sc);
		
		doNothing().when(shoppingCartRepository).delete(any(Integer.class));
		shoppingCartController.deleteShoppingCart(123);
	}
	
	@Test
	public void testAddOrderItem() {
		Category c = new Category();
		c.setName("Electronics");

		InventoryItem ii = new InventoryItem("iPhone6", 599.99, 100, c);
		ii.setInventoryItemId(121);
		when(inventoryItemRepository.findByInventoryItemId(121)).thenReturn(ii);
		
		ShoppingCart sc = new ShoppingCart();
		sc.setShoppingCartId(156);
		when(shoppingCartRepository.findByshoppingCartId(156)).thenReturn(sc);
		
		OrderItem oi = new OrderItem(2, ii, sc);
		when(orderItemRepository.save(any(OrderItem.class))).thenReturn(oi);
		
		LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
		properties.put("quantity", new Integer(2));
		properties.put("inventoryItemId", new Integer(121));
		properties.put("shoppingCartId", new Integer(156));
		OrderItem expected = shoppingCartController.addOrderItem(properties);
		assertEquals(expected.getInventoryItem().getInventoryItemId(), 121);
		assertEquals(expected.getShoppingCart().getShoppingCartId(), 156);
		assertEquals(expected.getQuantity(), 2);
		assertEquals(expected.getInventoryItem().getCategory().getName(), "Electronics");
		assertEquals(expected.getInventoryItem().getName(), "iPhone6");
	}	
}