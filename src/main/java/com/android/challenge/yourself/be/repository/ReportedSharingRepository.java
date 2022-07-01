package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.ReportedSharing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportedSharingRepository extends JpaRepository<ReportedSharing, Integer> {

    ReportedSharing findByUserIdAndSharedChallengeId(int userId, int commentId);
}
