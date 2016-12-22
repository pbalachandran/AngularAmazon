package com.amazon.account;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CreditCardRepository ccRepository;
		
	@RequestMapping(method = RequestMethod.POST, value = "/account")
	public Account createAccount(@RequestBody Account account) {
		return accountRepository.save(account);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/account/{username}")
	public Account findAccountByUsername(@PathVariable String username) {
		Account act = accountRepository.findByUsername(username);
		if (act == null) {
			act = new Account();
			act.setUsername("Username not found");
		}
		return act;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/accounts")
	public List<Account> findAllAccounts() {
		return accountRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/creditcard")
	public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {
		return ccRepository.save(creditCard);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/creditcard/{creditCardId}")
	public CreditCard findCreditCard(@PathVariable int creditCardId) {
		return ccRepository.findByCreditCardId(creditCardId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/creditcard/{creditCardId}")	
	public void deleteCreditCard(@PathVariable int creditCardId) {
		ccRepository.delete(creditCardId);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}