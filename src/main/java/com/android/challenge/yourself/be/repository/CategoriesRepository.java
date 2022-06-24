package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIsDeletedFalse();
}
