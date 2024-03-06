package com.tipTopBites.domain.security;

import java.util.List;

public interface CartItemService {

	List<CartItem> findByDeliveryCart(DeliveryCart deliveryCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addFoodToCartItem(Food food, User user, int qty);
	
}
