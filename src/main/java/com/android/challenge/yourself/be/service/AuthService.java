package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.core.AuthToken;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.repository.AuthRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveToken(AuthToken authToken) {
        boolean isSaved = false;
        authToken.setToken(passwordEncoder.encode(RandomStringUtils.randomAlphabetic(10)));
        AuthToken savedToken = authRepository.save(authToken);
        if (null != savedToken && savedToken.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    @Transactional
    public boolean expireTokens(String token) {
        boolean areOldTokensDeleted = false;
        AuthToken currentToken = authRepository.findByToken(token);
        if (null != currentToken) {
            authRepository.deleteByUserId(currentToken.getUser().getId());
            areOldTokensDeleted = true;
        }
        return areOldTokensDeleted;
    }

    public boolean isTokenValid(String token) {
        return null != authRepository.findByToken(token);
    }

    public User getUser(String token) {
        AuthToken currentToken = authRepository.findByToken(token);
        return currentToken.getUser();
    }
}
