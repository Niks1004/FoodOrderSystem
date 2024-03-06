package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.UserDelivery;
@Repository
public interface UserDeliveryRepository extends JpaRepository<UserDelivery, Long> {

	void delete(Long id);

	UserDelivery findOne(Long id);

}
