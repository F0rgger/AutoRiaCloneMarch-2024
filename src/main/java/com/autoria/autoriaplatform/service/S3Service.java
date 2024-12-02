package com.autoria.autoriaplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.logging.Logger;

@Service
public class S3Service {

    private static final Logger logger = Logger.getLogger(S3Service.class.getName());

    private final S3Client s3Client;
    private final String bucketName = "your-bucket-name";
    private final Region region = Region.of("us-east-1");

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // Метод для загрузки файла в S3
    public void uploadFile(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile(Paths.get("uploads"), file.getOriginalFilename(), ".tmp");
        file.transferTo(tempFile);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getOriginalFilename())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(tempFile));
        logger.info("File uploaded to S3: " + file.getOriginalFilename());
    }

    public ResponseInputStream<GetObjectResponse> downloadFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        return s3Client.getObject(getObjectRequest);
    }


    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(region)
                .build();
    }
}
