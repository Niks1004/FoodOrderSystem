package com.tipTopBites.domain.security;

public interface UserPaymentService {
	
	UserPayment findById(Long id);
	
	void removeById(Long id);

}
