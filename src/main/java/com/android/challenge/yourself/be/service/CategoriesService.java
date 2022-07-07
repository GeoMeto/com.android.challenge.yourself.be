package com.android.challenge.yourself.be.service;

import com.android.challenge.yourself.be.model.dto.CategoryDTO;
import com.android.challenge.yourself.be.model.entities.Category;
import com.android.challenge.yourself.be.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> getCategories() {
        return StreamSupport.stream(categoriesRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Category> getActiveCategories() {
        return categoriesRepository.findByIsDeletedFalse();
    }

    public boolean saveCategory(Category category) {
        boolean isSaved = false;
        Category savedCategory = categoriesRepository.save(category);
        if (null != savedCategory && savedCategory.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public boolean updateCategory(CategoryDTO categoryForUpdate) {
        boolean isUpdated = false;
        Category category = categoriesRepository.findById(categoryForUpdate.getId()).get();
        category.setName(categoryForUpdate.getName());
        category.setDeleted(categoryForUpdate.getIsDeleted() != null);
        Category updatedCategory = categoriesRepository.save(category);
        if (null != updatedCategory && updatedCategory.getUpdatedAt() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }

    public Category getCategory(int id) {
        return categoriesRepository.findById(id).get();
    }

    public boolean deleteCategory(int id) {
        boolean isDeleted = false;
        Optional<Category> category = categoriesRepository.findById(id);
        try {
            categoriesRepository.delete(category.get());
            isDeleted = true;
        } catch (Exception e) {
            log.error(e.toString());
            category.get().setDeleted(true);
            categoriesRepository.save(category.get());
            isDeleted = true;
        }

        return isDeleted;
    }
}
