package com.tipTopBites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Repository;

import com.tipTopBites.domain.security.PasswordResetToken;
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<Token, String> {

	PasswordResetToken findByToken(final String token);
	
	void save(PasswordResetToken myToken);

}
