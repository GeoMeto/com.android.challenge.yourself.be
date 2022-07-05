package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.dto.SharedChallengeMVC;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.service.SharingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("admin")
public class PostsController {

    @Autowired
    private SharingService sharingService;

    @GetMapping(value = {"/posts/page/{pageNum}"})
    public ModelAndView displayPostsPage(Model model, @PathVariable(name = "pageNum") int pageNum, @RequestParam(value = "date") String date) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("posts.html");
        Page<SharedChallenge> sharingsPage;
        List<SharedChallengeMVC> sharings;

        if (date.equals("")) {
            modelAndView.addObject("date", new Date());
            sharingsPage = sharingService.getSharingsPage(pageNum);
            model.addAttribute("totalPages", sharingsPage.getTotalPages());
            sharings = sharingsPage.get().map(x -> new SharedChallengeMVC(x)).collect(Collectors.toList());
        } else {
            LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            sharings = sharingService.getSharings(parsed).stream().map(x -> new SharedChallengeMVC(x)).collect(Collectors.toList());
            modelAndView.addObject("date", new SimpleDateFormat("yyyy-MM-dd").parse(parsed.toString()));
            model.addAttribute("totalPages", 1);
        }

        modelAndView.addObject("posts", sharings);
        model.addAttribute("currentPage", pageNum);
        return modelAndView;
    }

    @PostMapping(value = {"/post/delete/{id}"})
    public ModelAndView deletePost(@PathVariable int id) {
        sharingService.deleteSharing(id);
        return new ModelAndView("redirect:/admin/posts/page/1?date=");
    }
}
