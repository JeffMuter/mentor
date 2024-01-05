package com.techelevator.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcMentorDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMentorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getMentorNames(String username) {
        String sql = "SELECT name FROM mentors WHERE username = ?"; // Assuming you're filtering by username
        List<String> mentorNames = jdbcTemplate.query(sql, new StringRowMapper(), username);
        return mentorNames;
    }

    public ResponseEntity<String> addMentor(String username, String mentorName) {
        String sql = "INSERT INTO mentors (name, username) VALUES (?, ?)";
        jdbcTemplate.update(sql, mentorName, username);
        return ResponseEntity.ok("Mentor added successfully");
    }

    private static class StringRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("name");
        }
    }
}
