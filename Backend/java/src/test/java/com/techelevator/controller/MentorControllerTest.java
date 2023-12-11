package com.techelevator.dao;
//import java.src\main\java\com\techelevator\controller\MentorController.java.MentorController;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MentorControllerTest {

    private final MentorController mentorController = new MentorController();

    @Test
    void uploadAudioForMentorTest() throws IOException {
        // Load the sample audio file from the resources folder
        String testAudioFilename = "test_audio.mp3";
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/" + testAudioFilename);

        MultipartFile testAudioFile = new MockMultipartFile(
                testAudioFilename,
                testAudioFilename,
                "audio/mpeg",
                fileInputStream
        );

        // Call the transcribeUploadedAudio method
        File transcriptionFile = mentorController.transcribeAudioUsingGoogleSpeechToText(testAudioFile).getBody();

        // Check if file is a .txt file
        assertTrue(transcriptionFile.getName().endsWith(".txt"));

        // Read the file contents
        String fileContents = Files.readString(transcriptionFile.toPath());

        // Inspect first and last words in the file
        String[] words = fileContents.split("\\s+");
        assertTrue(words.length >= 20); // Assumes there are at least 20 words in the transcription

        System.out.println("First 20 words in the transcription:");
        for (int i = 0; i < 20; i++) {
            System.out.println(words[i]);
        }

        System.out.println("Last 10 words in the transcription:");
        for (int i = words.length - 10; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
}