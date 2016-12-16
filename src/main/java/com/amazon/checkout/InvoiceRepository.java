package com.amazon.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	public Invoice findByInvoiceId(int invoiceId);
}
