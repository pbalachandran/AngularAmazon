package com.amazon.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class CreditCardTypeController {
		
	@Autowired
	private CreditCardTypeRepository cctRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/creditcardtype")
	public CreditCardType createCreditCardType(@RequestBody CreditCardType creditCardType) {
		return cctRepository.save(creditCardType);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/creditcardtype/{creditCardTypeId}")
	public CreditCardType findCreditCardType(@PathVariable int creditCardTypeId) {
		return cctRepository.findByCreditCardTypeId(creditCardTypeId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/creditcardtype/{creditCardTypeId}")	
	public void deleteCreditCardType(@PathVariable int creditCardTypeId) {
		cctRepository.delete(creditCardTypeId);
	}
}