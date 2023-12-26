package com.employee.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.entity.User;
import com.employee.employee.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUserDetails()
    {
        System.out.println(" iam in get list..!");
        return userService.getUserDetails();
    }

    @PostMapping
    public User creatUser(@RequestBody User user)
    {
        System.out.println("Controllers user"+user);
        return userService.creatUser(user);
    }
    
}
