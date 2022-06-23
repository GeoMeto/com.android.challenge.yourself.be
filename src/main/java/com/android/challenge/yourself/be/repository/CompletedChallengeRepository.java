package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.CompletedChallenge;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompletedChallengeRepository extends PagingAndSortingRepository<CompletedChallenge, Integer> {

    List<CompletedChallenge> findByUserIdAndIsDeletedFalse(int userId, Sort sort);

    @Transactional
    @Modifying
    int softDeleteChallenge(int id);
}
