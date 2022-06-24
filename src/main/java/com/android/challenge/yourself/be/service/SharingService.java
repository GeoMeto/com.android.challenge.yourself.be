package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.like.Like;
import com.android.challenge.yourself.be.model.like.UserSharingLike;
import com.android.challenge.yourself.be.model.like.UserSharingLikeId;
import com.android.challenge.yourself.be.repository.SharedChallengeRepository;
import com.android.challenge.yourself.be.repository.UserSharingLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SharingService {
    @Autowired
    private SharedChallengeRepository sharedChallengeRepository;
    @Autowired
    private UserSharingLikeRepository userSharingLikeRepository;

    public List<SharedChallenge> getSharings() {
        return sharedChallengeRepository.findByIsDeletedFalseOrderByIdDesc();
    }

    public List<SharedChallenge> getSharings(int usedId) {
        return sharedChallengeRepository.findByUserIdAndIsDeletedFalseOrderByIdDesc(usedId);
    }

    public List<SharedChallenge> getHotSharings() {
        return sharedChallengeRepository.findByIsDeletedFalseAndCreatedAtBeforeOrderByLikesDesc(LocalDateTime.now().minusDays(3));
    }

    public boolean saveSharing(SharedChallenge sharedChallenge) {
        boolean isSaved = false;
        SharedChallenge savedSharing = sharedChallengeRepository.save(sharedChallenge);
        if (null != savedSharing && savedSharing.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Like likeSharing(int userId, int sharingId) {
        Like result = new Like();
        UserSharingLikeId id = new UserSharingLikeId(userId, sharingId);
        if (userSharingLikeRepository.findByUserId(userId).isEmpty()) {
            UserSharingLike like = new UserSharingLike();
            like.setUserSharingLikeId(id);
            userSharingLikeRepository.save(like);
            result.setSuccess(true);
        } else {
            userSharingLikeRepository.deleteById(id);
            result.setSuccess(false);
        }
        result.setCount(userSharingLikeRepository.findBySharingId(sharingId).size());
        return result;
    }
}
