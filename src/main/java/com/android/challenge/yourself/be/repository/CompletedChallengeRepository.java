package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.CompletedChallenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedChallengeRepository extends CrudRepository<CompletedChallenge, Integer> {

}
