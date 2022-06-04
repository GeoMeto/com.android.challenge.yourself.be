package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.Category;
import com.android.challenge.yourself.be.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value={"", "/", "categories"})
    public ModelAndView displayCategoriesPage(Model model) {
        List<Category> categories = categoriesService.getCategories();
        ModelAndView modelAndView = new ModelAndView("categories.html");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
