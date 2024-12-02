package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        s3Service.uploadFile(file);
        return "File uploaded successfully!";
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName) {
        s3Service.downloadFile(fileName);
        return "File downloaded successfully!";
    }
}
