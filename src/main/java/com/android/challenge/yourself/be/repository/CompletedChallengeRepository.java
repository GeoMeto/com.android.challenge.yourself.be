package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.CompletedChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedChallengeRepository extends JpaRepository<CompletedChallenge, Integer> {

}
