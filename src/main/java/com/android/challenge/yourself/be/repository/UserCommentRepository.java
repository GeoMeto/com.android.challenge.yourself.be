package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {

}
