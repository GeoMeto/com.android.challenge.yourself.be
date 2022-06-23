package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.like.UserSharingLike;
import com.android.challenge.yourself.be.model.like.UserSharingLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSharingLikeRepository extends JpaRepository<UserSharingLike, UserSharingLikeId> {

    List<UserSharingLike> findByUserId(int userId);
    List<UserSharingLike> findBySharingId(int sharingId);
}
