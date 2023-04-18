package com.appdev.social.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexPageController {

    @GetMapping
    public String displayIndexPage(Model model) {
        return "index";
    }

}
