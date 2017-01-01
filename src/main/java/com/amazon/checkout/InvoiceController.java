package com.amazon.checkout;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.account.CreditCard;
import com.amazon.account.CreditCardRepository;
import com.amazon.account.CreditCardType;
import com.amazon.shoppingcart.ShoppingCart;
import com.amazon.shoppingcart.ShoppingCartRepository;

@RestController
@RequestMapping("/checkout")
public class InvoiceController {
	
	private final String CHARGE = "CHARGE";
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ShoppingCartRepository scRepository;
	
	@Autowired
	private CreditCardRepository ccRepository;
	
	@Autowired
	private ShippingInfoRepository siRepository;
	
	private final double SALES_TAX = 0.08d;
	
	@RequestMapping(method = RequestMethod.POST, value = "/shippinginfo")
	public ShippingInfo createShippingInfo(@RequestBody LinkedHashMap<String, Object> properties) {
		String fname = String.valueOf(properties.get("firstname"));
		String lname = String.valueOf(properties.get("lastname"));
		String address = String.valueOf(properties.get("address"));
		String city = String.valueOf(properties.get("city"));
		String state = String.valueOf(properties.get("state"));
		String zip = String.valueOf(properties.get("zip"));
		if (fname != null && lname != null && address != null && 
			city != null && state != null && zip != null) {
			return siRepository.save(new ShippingInfo(fname, lname, address, city, state, zip));
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/invoice")
	public Invoice createInvoice(@RequestBody LinkedHashMap<String, Object> properties) {
		ShoppingCart sc = 
			scRepository.findByshoppingCartId(Integer.valueOf(String.valueOf(properties.get("shoppingCartId"))));
		double taxes = computeTaxes(sc);
		String confirmationCode = 
				chargeCreditCard(Integer.valueOf(String.valueOf(properties.get("creditCardId"))), 
								 sc.getTotal() + taxes);
		ShippingInfo si = 
			siRepository.findByShippingInfoId(Integer.valueOf(String.valueOf(properties.get("shippingInfoId"))));
		
		Invoice invoice = null;
		if (sc != null && si != null) {
			invoice = 
				invoiceRepository.save(new Invoice(sc.getTotal(),taxes,sc.getTotal() + taxes, 
								  	   			   confirmationCode,si,
								  	   			   new Date(new java.util.Date().getTime())));
			if (invoice != null) {
				sc.setInvoice(invoice);
				sc.setStatus("complete");
				scRepository.save(sc);
			}
		}
		return invoice;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/invoices/{username}")
	public Set<Invoice> retrieveInvoices(@PathVariable String username) {
		Set<ShoppingCart> carts = scRepository.findByUsernameAndStatus(username, "complete");
		Iterator<ShoppingCart> iter = carts.iterator();
		Set<Invoice> invoices = new HashSet<Invoice>();
		while(iter.hasNext()) {
			ShoppingCart sc = iter.next();
			invoices.add(sc.getInvoice());
		}
		return invoices;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/invoice/{invoiceId}")
	public Invoice retrieveInvoice(@PathVariable int invoiceId) {
		return invoiceRepository.findByInvoiceId(invoiceId);
	}
	
	private Double computeTaxes(ShoppingCart sc) {
		return new Double(new DecimalFormat("0.00").format(sc.getTotal() * SALES_TAX));
	}
	
	private String chargeCreditCard(int creditCardId, double inoviceTotal) {
		CreditCard creditCard = ccRepository.findByCreditCardId(creditCardId);
		if (creditCard != null) {
			CreditCardType creditCardType = creditCard.getCreditCardType();
			String confirmationCode = String.valueOf(new Random().nextInt(1000000));
			return CHARGE + ":" + creditCardType.getCreditCardTypeName() + "-" + confirmationCode;
		}
		return "Unable to charge credit card";
	}
}