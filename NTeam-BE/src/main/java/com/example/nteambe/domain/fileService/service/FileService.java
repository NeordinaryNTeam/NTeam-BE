package com.example.nteambe.domain.fileService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final List<String> ALLOWED_CONTENT_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/webp",
            "image/gif",
            "video/mp4",
            "video/quicktime",
            "video/x-msvideo",
            "video/x-matroska"
    );

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file) throws IOException {

        validateFile(file);

        String fileName = createFileName(file.getOriginalFilename());

        PutObjectRequest putObjectRequest =
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(fileName)
                        .contentType(file.getContentType())
                        .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromBytes(file.getBytes())
        );

        return getFileUrl(fileName);
    }

    public ResponseEntity<InputStreamResource> viewFile(String fileUrl) {

        String key = extractKeyFromUrl(fileUrl);

        GetObjectRequest getObjectRequest =
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();

        ResponseInputStream<GetObjectResponse> s3Object =
                s3Client.getObject(getObjectRequest);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType(
                                s3Object.response().contentType()
                        )
                )
                .body(new InputStreamResource(s3Object));
    }


    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                ALLOWED_CONTENT_TYPES.stream().noneMatch(type -> type.equalsIgnoreCase(contentType))) {

            throw new IllegalArgumentException("이미지 또는 영상 파일만 업로드 가능합니다.");
        }
    }

    private String createFileName(String originalFilename) {

        return UUID.randomUUID() + "_" + originalFilename;
    }

    private String getFileUrl(String fileName) {

        return String.format(
                "https://%s.s3.ap-northeast-2.amazonaws.com/%s",
                bucket,
                fileName
        );
    }

    private String extractKeyFromUrl(String fileUrl) {

        String baseUrl = String.format(
                "https://%s.s3.ap-northeast-2.amazonaws.com/",
                bucket
        );

        return fileUrl.replace(baseUrl, "");
    }
}