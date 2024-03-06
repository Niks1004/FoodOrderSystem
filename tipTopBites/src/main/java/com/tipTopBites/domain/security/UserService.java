package com.tipTopBites.domain.security;


import java.util.Set;

import org.springframework.stereotype.Service;


@Service
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserDelivery (UserDelivery userDelivery, User user);
	
	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void setUserDefaultDelivery(Long userDeliveryId, User user);

}
