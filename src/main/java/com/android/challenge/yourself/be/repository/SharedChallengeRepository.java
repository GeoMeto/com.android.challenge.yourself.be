package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.SharedChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SharedChallengeRepository extends PagingAndSortingRepository<SharedChallenge, Integer> {

    List<SharedChallenge> findByIsDeletedFalseOrderByIdDesc();
    List<SharedChallenge> findByUserIdAndIsDeletedFalseOrderByIdDesc(int usedId);
    List<SharedChallenge> findByIsDeletedFalseAndBeforeCreatedAtDateOrderByLikesDesc(LocalDateTime date);
}
