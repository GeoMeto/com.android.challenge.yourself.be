package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Integer> {

}
