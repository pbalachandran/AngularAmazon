package com.amazon.shoppingcart;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.account.Account;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	public ShoppingCart findByshoppingCartId(int shoppingCartId);
	public Set<ShoppingCart> findByAccountAndStatus(Account account, String status);
}