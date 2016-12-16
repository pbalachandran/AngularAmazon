package com.amazon.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazon.account.Account;
import com.amazon.account.AccountController;
import com.amazon.account.AccountRepository;
import com.amazon.account.CreditCard;
import com.amazon.account.CreditCardRepository;
import com.amazon.account.CreditCardType;
import com.amazon.account.CreditCardTypeRepository;


public class AccountControllerTest {

	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private CreditCardRepository ccRepository;
	
	@Mock
	private CreditCardTypeRepository cctRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void testCreateAccount() {
		CreditCardType cct = new CreditCardType();
		cct.setCreditCardTypeId(1);
		cct.setCreditCardTypeName("Discover");
		when(cctRepository.findByCreditCardTypeId(1)).thenReturn(cct);
		
		CreditCard cc = new CreditCard("Walt", "Disney", "12/20", "124", cct);
		cc.setCreditCardId(1);
		when(ccRepository.findByCreditCardId(1)).thenReturn(cc);
		
		Account a = new Account("wdisney", "Walt", "Disney", "disney", cc);
		when(accountRepository.save(a)).thenReturn(a);
		
		Account expected = accountController.createAccount(a);
		assertEquals(expected, a);
	}
	
	@Test
	public void testFindAccountByUsername() {
		CreditCardType cct = new CreditCardType();
		cct.setCreditCardTypeId(1);
		cct.setCreditCardTypeName("Discover");
		when(cctRepository.findByCreditCardTypeId(1)).thenReturn(cct);
		
		CreditCard cc = new CreditCard("Pradeep", "Balachandran", "12/20", "124", cct);
		cc.setCreditCardId(1);
		when(ccRepository.findByCreditCardId(1)).thenReturn(cc);

		Account a = new Account("pbalachandran", "Pradeep", "Balachandran", "amazon", cc);
		when(accountRepository.findByUsername("pbalachandran")).thenReturn(a);
		
		Account expected = accountController.findAccountByUsername("pbalachandran");
		assertEquals(expected.getFirstname(), "Pradeep");
	}
	
	@Test
	public void testFindAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		CreditCardType cct = new CreditCardType();
		cct.setCreditCardTypeId(1);
		cct.setCreditCardTypeName("Discover");
		when(cctRepository.findByCreditCardTypeId(1)).thenReturn(cct);
		
		CreditCard cc1 = new CreditCard("Walt", "Disney", "12/20", "124", cct);
		cc1.setCreditCardId(1);
		when(ccRepository.findByCreditCardId(1)).thenReturn(cc1);
		
		accounts.add(new Account("wdisney", "Walt", "Disney", "disney", cc1));
	
		CreditCard cc2 = new CreditCard("Roy", "Disney", "12/20", "124", cct);
		cc2.setCreditCardId(2);
		when(ccRepository.findByCreditCardId(2)).thenReturn(cc2);
		
		accounts.add(new Account("rdisney", "Roy", "Disney", "disney", cc2));
		when(accountRepository.findAll()).thenReturn(accounts);
		
		List<Account> expectedAccounts = accountController.findAllAccounts();
		assertEquals(expectedAccounts, accounts);
		verify(accountRepository, times(1)).findAll();
	}
}