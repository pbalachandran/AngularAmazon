package com.amazon.shoppingcart;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	public ShoppingCart findByshoppingCartId(int shoppingCartId);
	public Set<ShoppingCart> findByUsernameAndStatus(String username, String status);
}