package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SharedChallengeRepository extends PagingAndSortingRepository<SharedChallenge, Integer> {

    List<SharedChallenge> findByIsDeletedFalseOrderByIdDesc();
    List<SharedChallenge> findByUserIdAndIsDeletedFalseOrderByIdDesc(int usedId);
    List<SharedChallenge> findByIsDeletedFalseAndCreatedAtAfterOrderByLikesDesc(LocalDate date);
    List<SharedChallenge> findByIsDeletedFalseAndCreatedAtOrderByReportsDesc(LocalDate date);
    Page<SharedChallenge> findByIsDeletedFalseOrderByReportsDescCreatedAtDesc(Pageable pageable);
    @Transactional
    @Modifying
    int softDeleteSharedChallenge(int id);
}
