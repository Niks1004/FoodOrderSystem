package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.DeliveryCart;
@Repository
public interface DeliveryCartRepository extends JpaRepository<DeliveryCart, Long> {

}
