package com.sursindmitry.crud.controller;

import com.sursindmitry.crud.dto.CreateDto;
import com.sursindmitry.crud.model.User;
import com.sursindmitry.crud.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateDto dto) {
        System.out.println(dto);

        userService.create(dto);

        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }


    @GetMapping("/read-user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {

        User user = userService.findUserById(Long.parseLong(userId));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/read-all-users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody CreateDto dto, @PathVariable Long id) {

        userService.update(dto, id);

        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    ResponseEntity<String> deleteUserById(@PathVariable String userId) {

        userService.deleteUserById(Long.parseLong(userId));

        return new ResponseEntity<>("User delete", HttpStatus.OK);
    }
}
