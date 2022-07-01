package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.dto.EmailDto;
import com.android.challenge.yourself.be.model.dto.UserDTO;
import com.android.challenge.yourself.be.model.entities.Category;
import com.android.challenge.yourself.be.model.entities.Challenge;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.service.CategoriesService;
import com.android.challenge.yourself.be.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/users/page/{pageNum}"})
    public ModelAndView displayUsersPage(Model model, @PathVariable(name = "pageNum") int pageNum, @RequestParam("email") String email ){
        ModelAndView modelAndView = new ModelAndView("users.html");
        Page<User> usersPage = userService.getUsersByEmailSorted(pageNum, email);
        modelAndView.addObject("users", usersPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("email", new EmailDto(email));
        model.addAttribute("totalPages", usersPage.getTotalPages());

        return modelAndView;
    }

    @GetMapping(value = {"/users/edit/{id}"})
    public ModelAndView displayEditChallengePage(@PathVariable int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("edit-user.html");
        User user = userService.getUser(id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping(value = {"/user/{id}"})
    public ModelAndView updateUser(@PathVariable int id, @Valid UserDTO userDto, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();

        if (errors.hasErrors()) {
            log.error("Error when updating challenge: " + errors);
            modelAndView.setViewName("redirect:/admin/challenges/edit/ " + id);
            modelAndView.addObject("errorMessage", "All fields are mandatory!");
            return modelAndView;
        }

        userService.updateUser(userDto);
        modelAndView.setViewName("redirect:/admin/users/page/1?email=" + userDto.getEmail());
        return modelAndView;
    }

    @PostMapping(value = {"/user/delete/{id}"})
    public ModelAndView deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/users/page/1?email=");
    }
}
