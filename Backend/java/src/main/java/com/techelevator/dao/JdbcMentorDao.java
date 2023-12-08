package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMentorDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMentorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getMentorNames() {
        String sql = "SELECT name FROM mentors";
        List<String> mentorNames = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
        return mentorNames;
    }
}