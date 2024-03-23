package com.example.springboot_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForAllEmpsController {
    @GetMapping("/")
    public String getInfoForAllEmps() {
        return "view_for_all_employees";
    }
}
