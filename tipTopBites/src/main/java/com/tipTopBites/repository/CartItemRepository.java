package com.tipTopBites.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.CartItem;
import com.tipTopBites.domain.security.DeliveryCart;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByDeliveryCart(DeliveryCart deliveryCart);

}
