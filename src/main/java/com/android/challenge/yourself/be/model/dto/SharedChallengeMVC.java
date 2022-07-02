package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedChallengeMVC {

    private int id;
    private int likeCount;
    private int reportsCount;
    private String email;
    private String name;
    private String description;
    private String measurement;
    private String comment;

    public SharedChallengeMVC(SharedChallenge challenge) {
        this.setId(challenge.getId());
        this.setEmail(challenge.getUser().getEmail());
        this.setName(challenge.getCompletedChallenge().getName());
        this.setMeasurement(challenge.getCompletedChallenge().getMeasurement());
        this.setComment(challenge.getCompletedChallenge().getComment());
        this.setDescription(challenge.getCompletedChallenge().getDescription());
        this.setLikeCount(challenge.getLikes());
        this.setReportsCount(challenge.getReports());
    }
}
