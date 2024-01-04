package com.techelevator.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMentorDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMentorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getMentorNames(String username) {
        String sql = "SELECT name FROM mentors WHERE email = ? ";
        List<String> mentorNames = jdbcTemplate.query(sql, new Object[] {username}, (rs, rowNum) -> rs.getString("name"));
        return mentorNames;
    }

    public ResponseEntity<String> addMentor(String username, String mentorName) {
        String sql = "INSERT INTO mentors (name, username) VALUES (?, ?)";
        jdbcTemplate.update(sql, mentorName, username);
        return ResponseEntity.ok("Mentor added successfully");
    }
}