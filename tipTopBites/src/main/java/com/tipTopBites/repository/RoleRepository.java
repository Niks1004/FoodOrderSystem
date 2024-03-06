package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByname(String name);
}
