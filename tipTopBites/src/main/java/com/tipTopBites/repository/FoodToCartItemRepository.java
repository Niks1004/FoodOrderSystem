package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tipTopBites.domain.security.FoodToCartItem;

public interface FoodToCartItemRepository extends JpaRepository<FoodToCartItem, Long>{

}
