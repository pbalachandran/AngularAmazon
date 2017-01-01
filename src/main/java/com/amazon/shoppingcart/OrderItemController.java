package com.amazon.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping")
public class OrderItemController {
	
	@Autowired
	private OrderItemRepository oiRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/orderitem/{orderItemId}")	
	public OrderItem findShoppingCart(@PathVariable int orderItemId) {
		return oiRepository.findByOrderItemId(orderItemId);
	}
}
