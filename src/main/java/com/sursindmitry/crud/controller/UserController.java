package com.sursindmitry.crud.controller;

import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.service.RestService;
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
    private final RestService restService;

    public UserController(UserService userService, RestService restService) {
        this.userService = userService;
        this.restService = restService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(Principal principal) {

        User user = userService.findUserByEmail(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-rest")
    public ResponseEntity<String> getRest() {
        String cookie = restService.getAllUsers();
        String firstPartCode = restService.addUser(cookie);
        String secondPartCode = restService.updateUser(cookie);
        String thirdPartCode = restService.deleteUser(cookie);

        String concat = firstPartCode + secondPartCode + thirdPartCode;
        return new ResponseEntity<>(concat, HttpStatus.OK);
    }


}
