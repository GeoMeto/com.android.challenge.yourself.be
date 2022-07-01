package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.entities.Category;
import com.android.challenge.yourself.be.model.entities.Challenge;
import com.android.challenge.yourself.be.service.CategoriesService;
import com.android.challenge.yourself.be.service.ChallengesService;
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
public class ChallengesController {

    @Autowired
    private ChallengesService challengesService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = {"/challenges"})
    public ModelAndView displayCategoriesPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("challenges.html");
        List<Challenge> challenges = challengesService.getChallenges();
        modelAndView.addObject("challenges", challenges);
        return modelAndView;
    }

    @GetMapping(value = {"/challenges/edit/{id}"})
    public ModelAndView displayEditChallengePage(@PathVariable int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("edit-challenges.html");
        List<Category> categories = categoriesService.getActiveCategories();
        Challenge challenge = challengesService.getChallenge(id);
        modelAndView.addObject("challenge", challenge);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @GetMapping(value = {"/challenges/new"})
    public ModelAndView displayCreateChallengePage(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-challenges.html");
        List<Category> categories = categoriesService.getActiveCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("challenge", new Challenge());
        return modelAndView;
    }

    @PostMapping(value = {"/challenges"})
    public ModelAndView saveChallenge(@Valid @ModelAttribute("challenge") Challenge challenge, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();

        if (errors.hasErrors()) {
            log.error("Error when creating challenge: " + errors);
            modelAndView.setViewName("create-challenges.html");
            modelAndView.addObject("errorMessage", "All fields are mandatory!");
            return modelAndView;
        }

        challengesService.saveChallenge(challenge);
        modelAndView.setViewName("redirect:/admin/challenges");
        return modelAndView;
    }

    @PostMapping(value = {"/challenges/{id}"})
    public ModelAndView updateChallenge(@PathVariable int id, @Valid @ModelAttribute("challenge") Challenge challenge, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();

        if (errors.hasErrors()) {
            log.error("Error when updating challenge: " + errors);
            modelAndView.setViewName("redirect:/admin/challenges/edit/ " + id);
            modelAndView.addObject("errorMessage", "All fields are mandatory!");
            return modelAndView;
        }

        challenge.setId(id);
        challengesService.updateChallenge(challenge);
        modelAndView.setViewName("redirect:/admin/challenges");
        return modelAndView;
    }
}
