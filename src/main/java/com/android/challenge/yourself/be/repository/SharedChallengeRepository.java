package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.SharedChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedChallengeRepository extends JpaRepository<SharedChallenge, Integer> {

}
