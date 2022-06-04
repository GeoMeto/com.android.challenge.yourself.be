package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.Category;
import com.android.challenge.yourself.be.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> getCategories() {
        return categoriesRepository.getCategories();
    }
}
