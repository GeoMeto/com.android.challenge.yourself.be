package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {

}
