package com.sursindmitry.crud.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String printWelcome() {

        return "index";
    }

    @GetMapping("/create-user")
    public String createUserPage() {

        return "create-user";
    }

    @GetMapping("/read-users")
    public String readUsersPage() {

        return "read-users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        model.addAttribute("name", principal.getName());
        return "profile";
    }

}
