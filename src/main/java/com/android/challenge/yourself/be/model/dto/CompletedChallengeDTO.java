package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.CompletedChallenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedChallengeDTO {

    private int id;
    private String name;
    private String measurement;
    private String comment;
    private String description;
    private int result;
    private int target;
    private Boolean isShared;
    private Boolean isCompleted;
    private Boolean isDeleted;
    private int userId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CompletedChallengeDTO(CompletedChallenge challenge) {
        this.setId(challenge.getId());
        this.setName(challenge.getName());
        this.setMeasurement(challenge.getMeasurement());
        this.setComment(challenge.getComment());
        this.setDescription(challenge.getDescription());
        this.setResult(challenge.getResult());
        this.setTarget(challenge.getTarget());
        this.setIsShared(challenge.isShared());
        this.setIsCompleted(challenge.isCompleted());
        this.setIsDeleted(challenge.isDeleted());
        this.setUserId(challenge.getUser().getId());
        this.setCreatedAt(challenge.getCreatedAt());
        this.setUpdatedAt(challenge.getUpdatedAt());
    }
}
