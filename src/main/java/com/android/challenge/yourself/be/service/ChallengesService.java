package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.entities.Challenge;
import com.android.challenge.yourself.be.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChallengesService {
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengesService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> getChallenges() {
        return StreamSupport.stream(challengeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public boolean saveChallenge(Challenge challenge) {
        boolean isSaved = false;
        Challenge savedChallenge = challengeRepository.save(challenge);
        if (savedChallenge != null && savedChallenge.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public boolean updateChallenge(Challenge newChallenge) {
        boolean isUpdated = false;
        Optional<Challenge> challenge = challengeRepository.findById(newChallenge.getId());
        challenge.ifPresent(challenge1 -> {
            challenge1.setUpdatedAt(LocalDateTime.now());
            challenge1.setName(newChallenge.getName());
            challenge1.setCategory(newChallenge.getCategory());
            challenge1.setMeasurement(newChallenge.getMeasurement());
            challenge1.setDescription(newChallenge.getDescription());
            challenge1.setTarget(newChallenge.getTarget());
        });
        Challenge updatedChallenge = challengeRepository.save(challenge.get());
        if (null != updatedChallenge && updatedChallenge.getUpdatedAt() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }

    public Challenge getChallenge(int id) {
        return challengeRepository.findById(id).get();
    }
}
