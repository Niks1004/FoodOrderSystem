package com.tipTopBites.domain.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String DeliveryAddressName;
	private String DeliveryAddressStreet1;
	private String DeliveryAddressStreet2;
	private String DeliveryAddressCity;
	private String DeliveryAddressCountry;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeliveryAddressName() {
		return DeliveryAddressName;
	}

	public void setDeliveryAddressName(String deliveryAddressName) {
		DeliveryAddressName = deliveryAddressName;
	}

	public String getDeliveryAddressStreet1() {
		return DeliveryAddressStreet1;
	}

	public void setDeliveryAddressStreet1(String deliveryAddressStreet1) {
		DeliveryAddressStreet1 = deliveryAddressStreet1;
	}

	public String getDeliveryAddressStreet2() {
		return DeliveryAddressStreet2;
	}

	public void setDeliveryAddressStreet2(String deliveryAddressStreet2) {
		DeliveryAddressStreet2 = deliveryAddressStreet2;
	}

	public String getDeliveryAddressCity() {
		return DeliveryAddressCity;
	}

	public void setDeliveryAddressCity(String deliveryAddressCity) {
		DeliveryAddressCity = deliveryAddressCity;
	}

	public String getDeliveryAddressCountry() {
		return DeliveryAddressCountry;
	}

	public void setDeliveryAddressCountry(String deliveryAddressCountry) {
		DeliveryAddressCountry = deliveryAddressCountry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
