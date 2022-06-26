package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SharedChallengeRepository extends PagingAndSortingRepository<SharedChallenge, Integer> {

    List<SharedChallenge> findByIsDeletedFalseOrderByIdDesc();
    List<SharedChallenge> findByUserIdAndIsDeletedFalseOrderByIdDesc(int usedId);
    List<SharedChallenge> findByIsDeletedFalseAndCreatedAtBeforeOrderByLikesDesc(LocalDateTime date);
    @Transactional
    @Modifying
    int softDeleteSharedChallenge(int id);
}
