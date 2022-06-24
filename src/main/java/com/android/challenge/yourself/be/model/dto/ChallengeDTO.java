package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.Challenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO {

    private int id;
    private int target;
    private String name;
    private String measurement;
    private String category;
    private String description;
    private Boolean isPositive;

    public ChallengeDTO(Challenge challenge) {
        this.setId(challenge.getId());
        this.setTarget(challenge.getTarget());
        this.setName(challenge.getName());
        this.setMeasurement(challenge.getMeasurement());
        this.setCategory(challenge.getCategory().getName());
        this.setDescription(challenge.getDescription());
        this.setIsPositive(challenge.getIsPositive());
    }
}
