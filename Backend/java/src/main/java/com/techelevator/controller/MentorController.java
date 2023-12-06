package com.techelevator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MentorController {

    @GetMapping("/mentors")
    public String getMentor() {
        String mentorName = "";
        return "Welcome to the Mentor page!";
    }
}
