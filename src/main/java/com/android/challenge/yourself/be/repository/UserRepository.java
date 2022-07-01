package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

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

    Page<User> findByEmailContaining(String email, Pageable pageable);
}
