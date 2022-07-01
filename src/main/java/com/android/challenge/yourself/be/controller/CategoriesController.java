package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.dto.CategoryDTO;
import com.android.challenge.yourself.be.model.entities.Category;
import com.android.challenge.yourself.be.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = {"/categories"})
    public ModelAndView displayCategoriesPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("categories.html");
        List<Category> categories = categoriesService.getCategories();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping(value = {"/categories/edit/{id}"})
    public ModelAndView displayEditCategoryPage(@PathVariable int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("edit-categories.html");
        Category category = categoriesService.getCategory(id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("isDeleted", category.isDeleted());
        return modelAndView;
    }

    @GetMapping(value = {"/categories/new"})
    public ModelAndView displayCreateCategoryPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-categories.html");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping(value = {"/categories"})
    public ModelAndView saveCategory(@Valid @ModelAttribute("category") Category category, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            log.error("Error when creating category: " + errors);
            modelAndView.setViewName("redirect:/admin/categories/new");
            modelAndView.addObject("errorMessage", errors);
            return modelAndView;
        }
        categoriesService.saveCategory(category);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    @PostMapping(value = {"/categories/{id}"})
    public ModelAndView updateCategory(@PathVariable int id, @Valid @ModelAttribute("category") CategoryDTO categoryDTO, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            log.error("Error when creating category: " + errors);
            modelAndView.setViewName("redirect:/admin/categories/edit/" + id);
            modelAndView.addObject("errorMessage", "Name is mandatory!");
            return modelAndView;
        }

        categoriesService.updateCategory(categoryDTO);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    @PostMapping(value = {"/categories/delete/{id}"})
    public ModelAndView deleteCategory(@PathVariable int id) {
        categoriesService.deleteCategory(id);
        return new ModelAndView("redirect:/admin/categories");
    }
}
