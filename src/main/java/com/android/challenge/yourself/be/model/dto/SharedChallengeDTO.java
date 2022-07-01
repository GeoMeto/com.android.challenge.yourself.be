package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.UserComment;
import com.android.challenge.yourself.be.model.like.UserSharingLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedChallengeDTO {

    private int id;
    private Boolean isDeleted;
    private Boolean isLiked;
    private int ownerId;
    private int likeCount;
    private CompletedChallengeDTO completedChallengeDTO;
    private List<UserCommentDTO> userCommentDTO;
    private LocalDateTime createdAt;

    public SharedChallengeDTO(SharedChallenge challenge) {
        this.setId(challenge.getId());
        this.setIsDeleted(challenge.isDeleted());
        this.setOwnerId(challenge.getUser().getId());
        this.setCompletedChallengeDTO(new CompletedChallengeDTO(challenge.getCompletedChallenge()));
        this.setUserCommentDTO(
                challenge.getUserComments().stream().map(x -> new UserCommentDTO(x, ownerId == challenge.getUser().getId())).collect(Collectors.toList())
        );
        this.setCreatedAt(challenge.getCreatedAt());

        Set<UserSharingLike> likedSharings = challenge.getLikedSharings();
        this.setLikeCount(likedSharings.size());
        this.setIsLiked(false);
    }

    public SharedChallengeDTO(SharedChallenge challenge, int userId) {
        this.setId(challenge.getId());
        this.setIsDeleted(challenge.isDeleted());
        this.setOwnerId(challenge.getUser().getId());
        this.setCompletedChallengeDTO(new CompletedChallengeDTO(challenge.getCompletedChallenge()));
        this.setUserCommentDTO(
                challenge.getUserComments().stream().map(x -> new UserCommentDTO(x, ownerId == challenge.getUser().getId())).collect(Collectors.toList())
        );
        this.setCreatedAt(challenge.getCreatedAt());

        Set<UserSharingLike> likedSharings = challenge.getLikedSharings();
        this.setLikeCount(likedSharings.size());
        this.setIsLiked(likedSharings.stream().anyMatch(x -> x.getUser().getId().equals(userId)));
    }
}
