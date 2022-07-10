package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthToken, Integer> {
    AuthToken findByToken(String token);
    void deleteByUserId(int userid);
}
