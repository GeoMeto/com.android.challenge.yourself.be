package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.SharedChallenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedChallengeRepository extends CrudRepository<SharedChallenge, Integer> {

}
