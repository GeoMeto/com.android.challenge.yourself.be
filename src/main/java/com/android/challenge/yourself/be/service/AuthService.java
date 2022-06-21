package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.AuthToken;
import com.android.challenge.yourself.be.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean saveToken(AuthToken authToken) {
        boolean isSaved = false;
        AuthToken savedToken = authRepository.save(authToken);
        if (null != savedToken && savedToken.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public boolean expireTokens(String token) {
        boolean areOldTokensDeleted = false;
        List<AuthToken> currentTokens = authRepository.findAllByToken(token);
        if (currentTokens.size() > 0) {
            authRepository.deleteAll(currentTokens);
            areOldTokensDeleted = true;
        }
        return areOldTokensDeleted;
    }

    public boolean isTokenValid(String token) {
        return !authRepository.findAllByToken(token).isEmpty();
    }
}
