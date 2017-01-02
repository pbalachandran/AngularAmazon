package com.amazon.account;

import java.io.IOException;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "creditcardtype")
public class CreditCardType {
	
	@Id
	@GeneratedValue
	@Column(name="creditcardtypeid")
	private int creditCardTypeId;

		
	@Column(name = "creditcardtypename")
	private String creditCardTypeName;
	
	
	public CreditCardType() {		
	}
	
	
	public CreditCardType(String jsonURL) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		CreditCardType clone = mapper.readValue(new URL(jsonURL), CreditCardType.class);
		this.creditCardTypeName = clone.getCreditCardTypeName();	
	}
	
	public String getCreditCardTypeName() {
		return creditCardTypeName;
	}

	public void setCreditCardTypeName(String creditCardTypeName) {
		this.creditCardTypeName = creditCardTypeName;
	}


	public int getCreditCardTypeId() {
		return creditCardTypeId;
	}


	public void setCreditCardTypeId(int creditCardTypeId) {
		this.creditCardTypeId = creditCardTypeId;
	}
}