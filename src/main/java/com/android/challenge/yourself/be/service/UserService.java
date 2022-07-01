package com.android.challenge.yourself.be.service;


import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        boolean isUserCreated = false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Client");
        user.setIsDeleted(false);
        User createdUser = userRepository.save(user);
        if (createdUser != null && createdUser.getId() > 0) {
            isUserCreated = true;
        }
        return isUserCreated;
    }

    public boolean updatePassword(User user) {
        boolean isUserUpdated = false;
        int result = userRepository.updateUserPassword(passwordEncoder.encode(user.getPassword()), user.getId());
        if (result > 0) {
            isUserUpdated = true;
        }
        return isUserUpdated;
    }

    public boolean deleteUser(int id) {
        boolean isUserUpdated = false;
        int result = userRepository.softDeleteUser(id);
        if (result > 0) {
            isUserUpdated = true;
        }
        return isUserUpdated;
    }

    public boolean updateUsername(int id, String username) {
        boolean isUserUpdated = false;
        int result = userRepository.updateUsername(username, id);
        if (result > 0) {
            isUserUpdated = true;
        }
        return isUserUpdated;
    }

    public Page<User> getUsersByEmailSorted(int pageNum, String email) {
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("email"));

        return userRepository.findByEmailContaining(email,pageable);
    }
}
