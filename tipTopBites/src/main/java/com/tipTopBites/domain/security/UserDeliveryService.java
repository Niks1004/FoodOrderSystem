package com.tipTopBites.domain.security;

public interface UserDeliveryService {
	UserDelivery findById(Long id);
	
	void removeById(Long id);
	

}
