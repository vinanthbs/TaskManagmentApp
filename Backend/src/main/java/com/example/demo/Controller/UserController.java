package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.dto.UserResponseBody;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService UserService;

    private UserController(UserService UserService){
        this.UserService=UserService;
    }
    
    @PostMapping("/register")
    public UserResponseBody registerUser(@RequestBody User user) {
        
        return UserService.registerUser(user);
    }
    
    @PostMapping("/login")
    public String postMethodName(@RequestBody User user) {
        //TODO: process POST request
        
        return UserService.login(user);
    }
    
    
}
