package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.ReportedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportedCommentRepository extends JpaRepository<ReportedComment, Integer> {

    ReportedComment findByUserIdAndUserCommentId(int userId, int commentId);
}
