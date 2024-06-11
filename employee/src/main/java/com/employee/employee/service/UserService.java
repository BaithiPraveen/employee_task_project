package com.employee.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.employee.repository.UserRepository;
import com.employee.employee.entity.User;

@Service
public class UserService {
    
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getUserDetails()
    {
        return userRepository.findAll();
    }

    public User creatUser(User user)
    {
        // System.out.println("hii i am in me..!");
        // System.out.println("user details : " +user.getId()+user.getPassword()+user.getRoles()+user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
