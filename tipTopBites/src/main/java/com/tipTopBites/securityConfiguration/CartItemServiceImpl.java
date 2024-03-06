package com.tipTopBites.securityConfiguration;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipTopBites.domain.security.CartItem;
import com.tipTopBites.domain.security.CartItemService;
import com.tipTopBites.domain.security.DeliveryCart;
import com.tipTopBites.domain.security.Food;
import com.tipTopBites.domain.security.FoodToCartItem;
import com.tipTopBites.domain.security.User;
import com.tipTopBites.repository.CartItemRepository;
import com.tipTopBites.repository.FoodToCartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private FoodToCartItemRepository foodToCartItemRepository;
	
	public List<CartItem> findByDeliveryCart(DeliveryCart deliveryCart) {
		
		return cartItemRepository.findByDeliveryCart(deliveryCart);
	}
	
	@SuppressWarnings("deprecation")
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getFood().getPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}
	
	public CartItem addFoodToCartItem(Food food, User user, int qty)
	{
		List<CartItem> cartItemList = findByDeliveryCart(user.getDeliveryCart());
		
		for (CartItem cartItem : cartItemList) {
			if(food.getId() == cartItem.getFood().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(food.getPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setDeliveryCart(user.getDeliveryCart());
		cartItem.setFood(food);
		
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(food.getPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		FoodToCartItem foodToCartItem = new FoodToCartItem();
		foodToCartItem.setFood(food);
		foodToCartItem.setCartItem(cartItem);
		foodToCartItemRepository.save(foodToCartItem);
		
		return cartItem;


	}

}
