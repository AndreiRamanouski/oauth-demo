package com.appdev.social.login.controller;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomePageController {

    @GetMapping("home")
    public String getHomePage(Model model,
            @AuthenticationPrincipal OAuth2User principal) {
        if (Objects.nonNull(principal)) {
            String name = principal.getAttribute("name");
            model.addAttribute("name", name);
        }
        return "home-page";
    }

}
