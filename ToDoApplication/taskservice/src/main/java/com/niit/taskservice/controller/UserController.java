package com.niit.taskservice.controller;

import com.niit.taskservice.model.User;
import com.niit.taskservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService=userService;}

    @PostMapping("/user")
    public ResponseEntity<?> adduser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
}
