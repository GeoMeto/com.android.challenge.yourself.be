package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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
    }
}
