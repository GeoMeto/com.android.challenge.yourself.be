package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.User;
import com.android.challenge.yourself.be.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "public/createUser")
    public String createUser(@Valid @ModelAttribute("user") User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register.html";
        }
        boolean isSaved = userService.createUser(user);
        if (isSaved) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String displayLoginPage() {
        return "login.html";
    }
}
