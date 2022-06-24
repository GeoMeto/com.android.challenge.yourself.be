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
    private int userId;
    private CompletedChallengeDTO completedChallengeDTO;
    private List<UserCommentDTO> userCommentDTO;
    private LocalDateTime createdAt;

    public SharedChallengeDTO(SharedChallenge challenge) {
        this.setId(challenge.getId());
        this.setIsDeleted(challenge.isDeleted());
        this.setUserId(challenge.getUser().getId());
        this.setCompletedChallengeDTO(new CompletedChallengeDTO(challenge.getCompletedChallenge()));
        this.setUserCommentDTO(
                challenge.getUserComments().stream().map(UserCommentDTO::new).collect(Collectors.toList())
        );
        this.setCreatedAt(challenge.getCreatedAt());
    }
}
