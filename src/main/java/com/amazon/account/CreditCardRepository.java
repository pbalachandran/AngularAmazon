package com.amazon.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
	public CreditCard findByCreditCardId(int creditCardId);	
}
