package com.android.challenge.yourself.be.controller;

import com.android.challenge.yourself.be.model.dto.SharedChallengeMVC;
import com.android.challenge.yourself.be.model.dto.UserCommentMVC;
import com.android.challenge.yourself.be.model.entities.ReportedComment;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.UserComment;
import com.android.challenge.yourself.be.service.CommentService;
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
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = {"/comments/page/{pageNum}"})
    public ModelAndView displayCommentsPage(Model model, @PathVariable(name = "pageNum") int pageNum, @RequestParam(value = "date") String date) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("comments.html");
        Page<UserComment> reportedCommentsPage;
        List<UserCommentMVC> commentMVCS;

        if (date.equals("")) {
            modelAndView.addObject("date", new Date());
            reportedCommentsPage = commentService.getComments(pageNum);
            model.addAttribute("totalPages", reportedCommentsPage.getTotalPages());
            commentMVCS = reportedCommentsPage.get().map(x -> new UserCommentMVC(x)).collect(Collectors.toList());
        } else {
            LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            commentMVCS = commentService.getComments(parsed).stream().map(x -> new UserCommentMVC(x)).collect(Collectors.toList());
            modelAndView.addObject("date", new SimpleDateFormat("yyyy-MM-dd").parse(parsed.toString()));
            model.addAttribute("totalPages", 1);
        }

        modelAndView.addObject("comments", commentMVCS);
        model.addAttribute("currentPage", pageNum);
        return modelAndView;
    }

    @PostMapping(value = {"/comment/delete/{id}"})
    public ModelAndView deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        return new ModelAndView("redirect:/admin/comments/page/1?date=");
    }
}
