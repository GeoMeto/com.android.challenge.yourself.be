package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.UserSharingLike;
import com.android.challenge.yourself.be.model.entities.UserSharingLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserSharingLikeRepository extends JpaRepository<UserSharingLike, UserSharingLikeId> {
    @Query(value = "SELECT * FROM  user_sharing_likes usl WHERE usl.user_id = ?1 AND usl.sharing_id = ?2", nativeQuery = true)
    UserSharingLike findByUserIdAndSharingId(int userId, int sharingId);
    @Query(value = "SELECT * FROM  user_sharing_likes usl WHERE usl.sharing_id = ?1", nativeQuery = true)
    List<UserSharingLike> findBySharingId(int sharingId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM  user_sharing_likes usl WHERE usl.user_id = ?1 AND usl.sharing_id = ?2", nativeQuery = true)
    int deleteById(int userId, int sharingId);
}
