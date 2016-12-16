package com.amazon.checkout;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {
	public ShippingInfo findByShippingInfoId(int shippingInfoId);
}
