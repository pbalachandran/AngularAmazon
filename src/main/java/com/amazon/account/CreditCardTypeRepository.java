package com.amazon.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardTypeRepository extends JpaRepository<CreditCardType, Integer> {
	public CreditCardType findByCreditCardTypeId(int creditCardTypeId);
}
