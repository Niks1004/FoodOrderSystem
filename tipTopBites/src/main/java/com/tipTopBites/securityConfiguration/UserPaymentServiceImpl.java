package com.tipTopBites.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipTopBites.domain.security.UserPayment;
import com.tipTopBites.domain.security.UserPaymentService;
import com.tipTopBites.repository.UserPaymentRepository;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	public UserPayment findById(Long id) {
		return userPaymentRepository.findOne(id);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.delete(id);
	}

}
