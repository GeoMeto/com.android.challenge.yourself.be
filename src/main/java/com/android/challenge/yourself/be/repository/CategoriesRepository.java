package com.android.challenge.yourself.be.repository;

import com.android.challenge.yourself.be.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriesRepository {

    public List<Category> getCategories() {

        return List.of(new Category(2, "Health"));
    }
}
