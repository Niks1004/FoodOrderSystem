package com.tipTopBites.securityConfiguration;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tipTopBites.domain.security.DeliveryCart;
import com.tipTopBites.domain.security.PasswordResetToken;
import com.tipTopBites.domain.security.User;
import com.tipTopBites.domain.security.UserBilling;
import com.tipTopBites.domain.security.UserDelivery;
import com.tipTopBites.domain.security.UserPayment;
import com.tipTopBites.domain.security.UserRole;
import com.tipTopBites.domain.security.UserService;
import com.tipTopBites.repository.PasswordResetTokenRepository;
import com.tipTopBites.repository.RoleRepository;
import com.tipTopBites.repository.UserDeliveryRepository;
import com.tipTopBites.repository.UserPaymentRepository;
import com.tipTopBites.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	UserPaymentRepository userPaymentRepository;
	
	@Autowired
	UserDeliveryRepository userDeliveryRepository;
	
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
	
	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
	
	
	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("user {} already exists", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			DeliveryCart deliveryCart = new DeliveryCart();
			deliveryCart.setUser(user);
			user.setDeliveryCart(deliveryCart);
			
			user.setUserDeliveryList(new ArrayList<UserDelivery>());
			user.setUserPaymentList(new ArrayList<UserPayment>());

			
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
		
	}
	
	
	@Override
	public void updateUserDelivery (UserDelivery userDelivery, User user) {
		userDelivery.setUser(user);
		userDelivery.setUserDeliveryDefault(true);
		user.getUserDeliveryList().add(userDelivery);
		save(user);
		
	}

	
	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
		
		
		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId()==userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
				
			}else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);

			}
		}
	}
	
	@Override
	public void setUserDefaultDelivery(Long userDeliveryId, User user) {
		
List<UserDelivery> userDeliveryList = (List<UserDelivery>) userDeliveryRepository.findAll();
		
		
		for (UserDelivery userDelivery : userDeliveryList) {
			if(userDelivery.getId()==userDeliveryId) {
				userDelivery.setUserDeliveryDefault(true);
				userDeliveryRepository.save(userDelivery);
				
			}else {
				userDelivery.setUserDeliveryDefault(false);
				userDeliveryRepository.save(userDelivery);

			}
		}
		
	}


	
}
