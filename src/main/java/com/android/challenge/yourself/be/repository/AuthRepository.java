package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthToken, Integer> {
    AuthToken findByUserId(int usedId);
    AuthToken findByToken(String token);
    void deleteByUserId(int userid);
}
