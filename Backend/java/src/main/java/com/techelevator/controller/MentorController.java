package com.techelevator.controller;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.speech.v1.*;
import com.techelevator.dao.JdbcMentorDao;
import org.springframework.cglib.core.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Added import for MultipartFile

import java.io.*;
import java.nio.file.Files; // Added import for Files
import java.nio.file.Path; // Added import for Path
import java.nio.file.Paths; // Added import for Paths
import java.security.Principal;
import java.util.List;

import com.google.protobuf.ByteString;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;
import com.google.auth.oauth2.GoogleCredentials;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


@PreAuthorize("isAuthenticated()")
@RestController
public class MentorController {

    private final JdbcMentorDao jdbcMentorDao;

    public MentorController(JdbcMentorDao jdbcMentorDao) {
        this.jdbcMentorDao = jdbcMentorDao;
    }

    // TODO: finish
    @GetMapping("/getMentorNames")
    public List<String> getMentorNames(Principal principal) {
        String username = principal.getName();
        return jdbcMentorDao.getMentorNames(username);
    }

    @PostMapping("/addMentor/{name}")
    public ResponseEntity<String> addMentor(Principal principal, @PathVariable String name, @RequestParam("file") MultipartFile mp3File) {
        String username = principal.getName();
        String mentorName = name;
        return jdbcMentorDao.addMentor(username, mentorName);
    }
}