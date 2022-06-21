package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AuthRepository extends JpaRepository<AuthToken, Integer> {
    AuthToken findByUserId(int usedId);
    List<AuthToken> findAllByToken(String token);
}
