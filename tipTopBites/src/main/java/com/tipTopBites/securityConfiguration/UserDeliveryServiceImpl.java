package com.tipTopBites.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipTopBites.domain.security.UserDelivery;
import com.tipTopBites.domain.security.UserDeliveryService;
import com.tipTopBites.repository.UserDeliveryRepository;

@Service
public class UserDeliveryServiceImpl implements UserDeliveryService{
	
	@Autowired
	private UserDeliveryRepository userDeliveryRepository;
	
	public UserDelivery findById(Long id) {
		return userDeliveryRepository.findOne(id);
	}
	
	public void removeById(Long id) {
		userDeliveryRepository.delete(id);;
	}


}
