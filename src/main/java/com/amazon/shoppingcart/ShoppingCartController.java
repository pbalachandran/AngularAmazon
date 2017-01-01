package com.amazon.shoppingcart;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.account.Account;
import com.amazon.account.AccountRepository;
import com.amazon.catalog.InventoryItem;
import com.amazon.catalog.InventoryItemRepository;

@RestController
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	@Autowired
	private AccountRepository actRepository;
	
	@Autowired
	private ShoppingCartRepository scRepository;
	
	@Autowired
	private OrderItemRepository oiRepository;
	
	@Autowired
	private InventoryItemRepository iiRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cart/{username}")
	public ShoppingCart createShoppingCart(@PathVariable String username) {
		Account act = actRepository.findByUsername(username);		
		if (act != null) {
			ShoppingCart sc = new ShoppingCart(username);
			return scRepository.save(sc);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/cart/{shoppingCartId}")
	public boolean deleteShoppingCart(@PathVariable int shoppingCartId) {
		// Increase inventory & save InventoryItem(s)
		ShoppingCart sc = scRepository.findByshoppingCartId(shoppingCartId);
		if (sc.getOrderItems() != null && !sc.getOrderItems().isEmpty()) {
			Iterator<OrderItem> iter = sc.getOrderItems().iterator();
			while(iter.hasNext()) {
				OrderItem oi = iter.next();
				InventoryItem ii = oi.getInventoryItem();
				ii.setInventory(ii.getInventory() + oi.getQuantity());
				iiRepository.save(ii);
			}
		}
		scRepository.delete(shoppingCartId);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/carts/{username}/{status}")	
	public Set<ShoppingCart> findShoppingCartsByUsernameStatus(@PathVariable String username, 
															   @PathVariable String status) {
		Account act = actRepository.findByUsername(username);
		if (act != null) {
			return scRepository.findByUsernameAndStatus(username, status);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart/{shoppingCartId}")	
	public ShoppingCart findShoppingCart(@PathVariable int shoppingCartId) {
		return scRepository.findByshoppingCartId(shoppingCartId);
	}

		
	@RequestMapping(method = RequestMethod.POST, value = "/order")	
	public OrderItem addOrderItem(@RequestBody LinkedHashMap<String, Object> properties) {		
		InventoryItem ii = 
			iiRepository.findByInventoryItemId(Integer.valueOf(String.valueOf(properties.get("inventoryItemId"))));
		ShoppingCart sc = 
			scRepository.findByshoppingCartId(Integer.valueOf(String.valueOf(properties.get("shoppingCartId"))));
		OrderItem oi = null;
		if (ii != null && sc != null) {						
			sc.getOrderItems().add((oi = oiRepository.save(new OrderItem(Integer.valueOf(String.valueOf(properties.get("quantity"))),ii, sc))));

			// Decrease inventory & save InventoryItem
			ii.setInventory(ii.getInventory() - oi.getQuantity());
			iiRepository.save(ii);
			
			// Update shoppingcart total
			updateTotal(sc);
			scRepository.save(sc);
		}
		return oi;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/order")	
	public boolean removeOrderItem(@RequestParam int shoppingCartId, @RequestParam int orderItemId) {
		OrderItem oi = oiRepository.findByOrderItemId(orderItemId);
		if (oi != null) {
			ShoppingCart sc = 
				scRepository.findByshoppingCartId(shoppingCartId);
			if (sc != null) {
				sc.getOrderItems().remove(oi);
				oiRepository.delete(orderItemId);
				
				// Increase inventory & save InventoryItem
				InventoryItem ii = oi.getInventoryItem(); 
				ii.setInventory(ii.getInventory() + oi.getQuantity());				
				iiRepository.save(ii);
				
				// Update shoppingcart total
				updateTotal(sc);
				scRepository.save(sc);
				return true;
			}
		}
		return false;
	}
	
	private void updateTotal(ShoppingCart sc) {
		Iterator<OrderItem> iter = sc.getOrderItems().iterator();
		double total = 0.0d;
		while(iter.hasNext()) {
			OrderItem oi = iter.next();
			total += oi.getInventoryItem().getUnitPrice() * oi.getQuantity();
		}
		sc.setTotal(new Double(new DecimalFormat("0.00").format(total)));
	}
}