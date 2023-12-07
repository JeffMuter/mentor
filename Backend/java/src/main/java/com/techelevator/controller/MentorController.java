package com.techelevator.controller;

import com.techelevator.dao.JdbcMentorDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MentorController {

    private final JdbcMentorDao jdbcMentorDao;

    public MentorController(JdbcMentorDao jdbcMentorDao) {
        this.jdbcMentorDao = jdbcMentorDao;
    }

    @GetMapping("/mentors")
    public List<String> getMentorNames() {
        return jdbcMentorDao.getAllMentorNames();
    }
}
