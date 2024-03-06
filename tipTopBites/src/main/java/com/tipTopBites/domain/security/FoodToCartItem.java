package com.tipTopBites.domain.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FoodToCartItem {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="food_id")	
	private Food food;
	
	
	@ManyToOne
	@JoinColumn(name="cart_item_id")	
	private CartItem cartItem;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Food getFood() {
		return food;
	}


	public void setFood(Food food) {
		this.food = food;
	}


	public CartItem getCartItem() {
		return cartItem;
	}


	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}


	
	
	
	
}
