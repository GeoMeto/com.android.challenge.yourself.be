package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.ReportedComment;
import com.android.challenge.yourself.be.model.entities.UserComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {
    List<UserComment> findByCreatedAtOrderByReportsDesc(LocalDate date);
    Page<UserComment> findByOrderByReportsDescCreatedAtDesc(Pageable pageable);

}
