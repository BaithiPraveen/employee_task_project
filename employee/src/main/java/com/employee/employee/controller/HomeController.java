package com.employee.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String Homepage()
    {
        return "Wel come to osi..!";
    }
}
