package com.techelevator.controller;

import com.techelevator.dao.JdbcMentorDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile; // Added import for MultipartFile
import java.io.File; // Added import for File
import java.io.IOException; // Added import for IOException
import java.nio.file.Files; // Added import for Files
import java.nio.file.Path; // Added import for Path
import java.nio.file.Paths; // Added import for Paths
import java.nio.file.StandardCopyOption; // Added import for StandardCopyOption
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@RestController
public class MentorController {

    private final JdbcMentorDao jdbcMentorDao;

    public MentorController(JdbcMentorDao jdbcMentorDao) {
        this.jdbcMentorDao = jdbcMentorDao;
    }

    @GetMapping("/mentors")
    public List<String> getMentorNames() {
        return jdbcMentorDao.getMentorNames();
    }

    @PostMapping("/newMentor") // needs updated to send information to db, just working on getting transcribing for now.
    public ResponseEntity<?> transcribeUploadedAudio(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a valid audio file.", HttpStatus.BAD_REQUEST);
        }
        File transcription;
        try {
            transcription = transcribeAudioUsingGoogleSpeechToText(file);
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>("Error processing the audio file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(transcription);
    }

    private File transcribeAudioUsingGoogleSpeechToText(MultipartFile file) throws IOException, InterruptedException {
        ByteString audioData = ByteString.readFrom(file.getInputStream());
        RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder().setContent(audioData).build();

        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setLanguageCode("en-US")
                .setSampleRateHertz(16000)
                .build();

        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognizeResponse response = speechClient.recognize(config, recognitionAudio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            String transcription = results.stream()
                    .map(SpeechRecognitionResult::getAlternativesList)
                    .flatMap(alternatives -> alternatives.stream())
                    .map(SpeechRecognitionAlternative::getTranscript)
                    .collect(Collectors.joining("\n"));

            return saveTranscriptionAsFile(transcription);
        }
    }

    private File saveTranscriptionAsFile(String transcription) throws IOException {
        // Generate a unique filename
        String uniqueFilename = "transcription_" + System.currentTimeMillis() + ".txt";
        Path filePath = Paths.get(uniqueFilename);

        // Save transcription to a file
        Files.write(filePath, transcription.getBytes(StandardCharsets.UTF_8));

        // Return the .txt file
        return filePath.toFile();
    }
}