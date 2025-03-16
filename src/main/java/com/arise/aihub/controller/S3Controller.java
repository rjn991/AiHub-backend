package com.arise.aihub.controller;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/s3")
@CrossOrigin(origins = "*")
public class S3Controller {

    private final S3Presigner s3Presigner;
    private final Dotenv dotenv = Dotenv.load(); // Load .env file

    public S3Controller(S3Presigner s3Presigner) {
        this.s3Presigner = s3Presigner;
    }

    @GetMapping("/generate-put-url")
    public Map<String, String> getPresignedPutUrl(@RequestParam String fileName) {
        String bucketName = dotenv.get("AWS_S3_BUCKET"); // Load bucket name from .env

        // Extract file extension
        String fileExtension = "";
        int lastDot = fileName.lastIndexOf(".");
        if (lastDot != -1 && lastDot < fileName.length() - 1) {
            fileExtension = fileName.substring(lastDot);
        }

        // Generate a random file name while keeping the extension
        String randomFileName = UUID.randomUUID().toString() + fileExtension;

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15)) // URL valid for 15 minutes
                .putObjectRequest(req -> req.bucket(bucketName).key(randomFileName))
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        URL url = presignedRequest.url();

        Map<String, String> response = new HashMap<>();
        response.put("fileName", randomFileName);
        response.put("presignedUrl", url.toString());
        return response;
    }
}