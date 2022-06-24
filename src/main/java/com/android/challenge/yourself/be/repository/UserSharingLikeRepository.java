package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.like.UserSharingLike;
import com.android.challenge.yourself.be.model.like.UserSharingLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSharingLikeRepository extends JpaRepository<UserSharingLike, UserSharingLikeId> {
    @Query(value = "SELECT * FROM  user_sharing_likes usl WHERE usl.userId = ?1", nativeQuery = true)
    List<UserSharingLike> findByUserId(int userId);
    @Query(value = "SELECT * FROM  user_sharing_likes usl WHERE usl.sharing_id = ?1", nativeQuery = true)
    List<UserSharingLike> findBySharingId(int sharingId);
}
