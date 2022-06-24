package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.entities.CompletedChallenge;
import com.android.challenge.yourself.be.repository.CompletedChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletedChallengesService {

    @Autowired
    private CompletedChallengeRepository completedChallengeRepository;

    public List<CompletedChallenge> getCompletedChallenges(int userId) {
        return completedChallengeRepository.findByUserIdAndIsDeletedFalse(userId, Sort.by("id").descending());
    }

    public boolean saveChallenge(CompletedChallenge challenge) {
        boolean isSaved = false;
        CompletedChallenge savedChallenge = completedChallengeRepository.save(challenge);
        if (null != savedChallenge && savedChallenge.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public boolean deleteChallenge(int id) {
        int result = completedChallengeRepository.softDeleteChallenge(id);
        return result > 0;
    }
}
