package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.dto.UserCommentDTO;
import com.android.challenge.yourself.be.model.entities.ReportedSharing;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.model.like.LikesDTO;
import com.android.challenge.yourself.be.model.like.UserSharingLike;
import com.android.challenge.yourself.be.model.like.UserSharingLikeId;
import com.android.challenge.yourself.be.repository.ReportedSharingRepository;
import com.android.challenge.yourself.be.repository.SharedChallengeRepository;
import com.android.challenge.yourself.be.repository.UserSharingLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharingService {
    @Autowired
    private SharedChallengeRepository sharedChallengeRepository;
    @Autowired
    private UserSharingLikeRepository userSharingLikeRepository;
    @Autowired
    private ReportedSharingRepository reportedSharingRepository;

    private final static int pageSize = 5;

    public List<SharedChallenge> getSharings() {
        return sharedChallengeRepository.findByIsDeletedFalseOrderByIdDesc();
    }

    public List<SharedChallenge> getSharings(int usedId) {
        return sharedChallengeRepository.findByUserIdAndIsDeletedFalseOrderByIdDesc(usedId);
    }

    public Page<SharedChallenge> getSharingsPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return sharedChallengeRepository.findByIsDeletedFalseOrderByReportsDescCreatedAtDesc(pageable);
    }

    public List<SharedChallenge> getSharings(LocalDate date) {
        return sharedChallengeRepository.findByIsDeletedFalseAndCreatedAtOrderByReportsDesc(date);
    }

    public List<SharedChallenge> getHotSharings() {
        return sharedChallengeRepository.findByIsDeletedFalseAndCreatedAtAfterOrderByLikesDesc(LocalDate.now().minusDays(3));
    }

    public boolean saveSharing(SharedChallenge sharedChallenge) {
        boolean isSaved = false;
        sharedChallenge.getCompletedChallenge().setShared(true);
        SharedChallenge savedSharing = sharedChallengeRepository.save(sharedChallenge);
        if (null != savedSharing && savedSharing.getId() > 0) {
            isSaved = true;

        }
        return isSaved;
    }

    public LikesDTO likeSharing(User user, int sharingId) {
        LikesDTO result = new LikesDTO();
        UserSharingLikeId id = new UserSharingLikeId(user.getId(), sharingId);
        SharedChallenge sharedChallenge = sharedChallengeRepository.findById(sharingId).get();

        if (null == userSharingLikeRepository.findByUserIdAndSharingId(user.getId(), sharingId)) {
            UserSharingLike like = new UserSharingLike();
            like.setUserSharingLikeId(id);
            like.setUser(user);
            like.setSharedChallenge(sharedChallenge);
            userSharingLikeRepository.save(like);
            result.setLiked(true);
        } else {
            userSharingLikeRepository.deleteById(id.getUserId(), id.getSharingId());
            result.setLiked(false);
        }
        int likesCount = userSharingLikeRepository.findBySharingId(sharingId).size();
        sharedChallenge.setLikes(likesCount);
        sharedChallengeRepository.save(sharedChallenge);
        result.setLikesCount(likesCount);
        return result;
    }

    public int deleteSharing(int id) {
        return sharedChallengeRepository.softDeleteSharedChallenge(id);
    }

    public List<UserCommentDTO> getSharingComments(int sharingId, int ownerId) {
        return sharedChallengeRepository.findById(sharingId).get().getUserComments().stream().map(x -> new UserCommentDTO(x, x.getUser().getId() == ownerId)).collect(Collectors.toList());
    }

    public SharedChallenge getSharing(int id) {
        return sharedChallengeRepository.findById(id).get();
    }

    public boolean saveReportedSharing(ReportedSharing reportedSharing) {
        boolean isSaved = false;
        ReportedSharing foundComment = reportedSharingRepository.findByUserIdAndSharedChallengeId(reportedSharing.getUser().getId(), reportedSharing.getSharedChallenge().getId());
        if (null == foundComment) {
            reportedSharingRepository.save(reportedSharing);
            SharedChallenge sharedChallenge = sharedChallengeRepository.findById(reportedSharing.getSharedChallenge().getId()).get();
            sharedChallenge.setReports(sharedChallenge.getReports() + 1);
            sharedChallengeRepository.save(sharedChallenge);
            isSaved = true;
        }
        return isSaved;
    }
}
