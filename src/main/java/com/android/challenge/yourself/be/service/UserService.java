package com.android.challenge.yourself.be.service;


import com.android.challenge.yourself.be.model.User;
import com.android.challenge.yourself.be.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        boolean isUserCreated = false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        if (createdUser != null && createdUser.getId() > 0) {
            isUserCreated = true;
        }
        return isUserCreated;
    }
}
