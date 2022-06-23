package com.android.challenge.yourself.be.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping
public class PublicController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String displayLoginPage() {
        return "login.html";
    }
}
