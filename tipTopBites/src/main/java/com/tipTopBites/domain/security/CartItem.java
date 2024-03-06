package com.tipTopBites.domain.security;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private int qty;
	private BigDecimal subtotal;
	
	@OneToOne
	private Food food;
	
	@OneToMany(mappedBy = "cartItem")
	@JsonIgnore
	private List<FoodToCartItem> foodToCartItemList;
	
	@ManyToOne
	@JoinColumn(name="delivery_cart_id")
	private DeliveryCart deliveryCart;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public List<FoodToCartItem> getFoodToCartItemList() {
		return foodToCartItemList;
	}

	public void setFoodToCartItemList(List<FoodToCartItem> foodToCartItemList) {
		this.foodToCartItemList = foodToCartItemList;
	}

	public DeliveryCart getDeliveryCart() {
		return deliveryCart;
	}

	public void setDeliveryCart(DeliveryCart deliveryCart) {
		this.deliveryCart = deliveryCart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	
	
	
}
