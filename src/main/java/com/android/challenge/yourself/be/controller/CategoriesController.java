package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.Category;
import com.android.challenge.yourself.be.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = {"", "/", "categories"}, method = RequestMethod.GET)
    public ModelAndView displayCategoriesPage(Model model) {
        List<Category> categories = categoriesService.getCategories();
        ModelAndView modelAndView = new ModelAndView("categories.html");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = {"/category/edit/{id}"}, method = RequestMethod.GET)
    public String displayEditCategoryPage(@PathVariable int id, Model model) {
        Category category = categoriesService.getCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("isDeleted", category.isDeleted());
        return "edit-categories.html";
    }

    @RequestMapping(value = {"create-category"}, method = RequestMethod.GET)
    public String displayCreateCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "create-categories.html";
    }

    @RequestMapping(value = {"categories"}, method = RequestMethod.POST)
    public String saveCategory(@Valid @ModelAttribute("category") Category category, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Error when creating category: " + errors);
        }
        categoriesService.saveCategory(category);
        return "categories.html";
    }

    @RequestMapping(value = {"categories/{id}"}, method = RequestMethod.PUT)
    public String updateCategory(@PathVariable int id, @Valid @ModelAttribute("category") Category category, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Error when creating category: " + errors);
        }
        category.setId(id);
        categoriesService.updateCategory(category);
        return "categories.html";
    }
}