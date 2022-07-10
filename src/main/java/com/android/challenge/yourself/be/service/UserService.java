package com.android.challenge.yourself.be.service;


import com.android.challenge.yourself.be.constants.Role;
import com.android.challenge.yourself.be.model.dto.UserDTO;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final int pageSize = 20;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        boolean isUserCreated = false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CLIENT.name());
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
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("email"));

        return userRepository.findByEmailContaining(email,pageable);
    }

    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    public boolean updateUser(UserDTO userDto) {
        boolean isUserUpdated = false;

        User foundUser = userRepository.findById(userDto.getId()).get();
        foundUser.setIsDeleted(userDto.getIsDeleted() != null);
        foundUser.setRole(userDto.getRole());
        foundUser.setEmail(userDto.getEmail());
        foundUser.setUsername(userDto.getUsername());

        User result = userRepository.save(foundUser);
        if (null != result) {
            isUserUpdated = true;
        }
        return isUserUpdated;
    }
}
