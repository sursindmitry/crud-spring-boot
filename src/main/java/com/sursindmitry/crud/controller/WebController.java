package com.sursindmitry.crud.controller;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/delete-user")
    public String deleteUserPage() {

        return "delete-user";
    }

    @GetMapping("/update-user")
    public String updateUserPage() {

        return "update-user";
    }

}
