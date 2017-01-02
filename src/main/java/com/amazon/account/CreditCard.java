package com.amazon.account;

import java.io.IOException;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.CascadeType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "creditcard")
public class CreditCard {

	@Id
	@GeneratedValue
	@Column(name="creditcardid")
	private int creditCardId;
	
	@Column(name = "creditcardnumber")
	private String creditCardNumber;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="creditcardtypeid")
	private CreditCardType creditCardType;
				
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "expirydate")
	private String expiryDate;
	
	@Column(name = "securitycode")
	private String securityCode;

	public CreditCard() {
	}
	
	public CreditCard(String firstname, String lastname, String expiryDate, 
					  String securityCode, CreditCardType creditCardType) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.expiryDate = expiryDate;
		this.securityCode = securityCode;
		this.creditCardType = creditCardType;		
	}
	
	public CreditCard(String jsonURL) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		CreditCard clone = mapper.readValue(new URL(jsonURL), CreditCard.class);
		this.creditCardId = clone.getCreditCardId();
		this.creditCardNumber = clone.getCreditCardNumber();
		this.firstname = clone.getFirstname();
		this.lastname = clone.getLastname();
		this.expiryDate = clone.getExpiryDate();
		this.securityCode = clone.getSecurityCode();
		this.creditCardType = clone.getCreditCardType();
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public int getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(int creditCardId) {
		this.creditCardId = creditCardId;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}
}