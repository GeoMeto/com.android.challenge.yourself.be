package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

}
