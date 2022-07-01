package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.dto.EmailDto;
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
}
