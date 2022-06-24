package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User readByEmail(String email);

    @Transactional
    @Modifying
    int updateUserPassword(String password, int id);

    @Transactional
    @Modifying
    int softDeleteUser(int id);

    @Transactional
    @Modifying
    int updateUsername(String username, int id);
}
