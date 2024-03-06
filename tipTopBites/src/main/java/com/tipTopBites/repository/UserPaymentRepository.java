package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.UserPayment;
@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long>{

	UserPayment findOne(Long id);

	void delete(Long id);

}
