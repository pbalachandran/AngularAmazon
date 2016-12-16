package com.amazon.checkout;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice {
		
	@Id
	@GeneratedValue	
	@Column(name="invoiceid")
	private int invoiceId;
	
	@Column(name="totalbeforetaxes")
	private Double totalBeforeTaxes;
	
	@Column(name="taxes")
	private Double taxes;

	@Column(name="invoicetotal")
	private Double invoiceTotal;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="shippinginfoid")
	private ShippingInfo shippingInfo;
	
	@Column(name="transactionconfirmation")
	private String transactionConfirmation;
	
	@Column(name="date")
	private Date date;
		
	public Invoice() {
	}
	
	public Invoice(Double totalBeforeTaxes, Double taxes, Double invoiceTotal, 
				   String transactionConfirmation, ShippingInfo shippingInfo,
				   Date date) {
		this.totalBeforeTaxes = totalBeforeTaxes;
		this.taxes = taxes;
		this.invoiceTotal = invoiceTotal;
		this.transactionConfirmation = transactionConfirmation;
		this.shippingInfo = shippingInfo;
		this.date = date;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Double getTotalBeforeTaxes() {
		return totalBeforeTaxes;
	}

	public void setTotalBeforeTaxes(Double totalBeforeTaxes) {
		this.totalBeforeTaxes = totalBeforeTaxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getTaxes() {
		return taxes;
	}

	public Double getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(Double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public String getTransactionConfirmation() {
		return transactionConfirmation;
	}

	public void setTransactionConfirmation(String transactionConfirmation) {
		this.transactionConfirmation = transactionConfirmation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}