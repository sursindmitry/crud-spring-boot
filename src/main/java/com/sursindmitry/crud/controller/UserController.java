package com.sursindmitry.crud.controller;

import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.service.UserService;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUser(Principal principal) {

        User user = userService.findUserByEmail(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
