package com.tipTopBites.domain.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String foodName;
	private String type;
	
	private String category;
	private String numberOfCalories;
	private String allergyType;
	private double price;
	
	private boolean active=true;
	
	@Column(columnDefinition="text")
	private String description;
	
	@Transient
	private MultipartFile foodImage;
	
	@OneToMany(mappedBy = "food")
	@JsonIgnore
	private List<FoodToCartItem> foodToCartItemList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNumberOfCalories() {
		return numberOfCalories;
	}

	public void setNumberOfCalories(String numberOfCalories) {
		this.numberOfCalories = numberOfCalories;
	}

	public String getAllergyType() {
		return allergyType;
	}

	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(MultipartFile foodImage) {
		this.foodImage = foodImage;
	}

	public List<FoodToCartItem> getFoodToCartItemList() {
		return foodToCartItemList;
	}

	public void setFoodToCartItemList(List<FoodToCartItem> foodToCartItemList) {
		this.foodToCartItemList = foodToCartItemList;
	}

	
	
}
