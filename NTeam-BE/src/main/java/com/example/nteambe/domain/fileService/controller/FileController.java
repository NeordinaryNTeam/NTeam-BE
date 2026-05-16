package com.example.nteambe.domain.fileService.controller;

import com.example.nteambe.domain.fileService.dto.response.FileResDto;
import com.example.nteambe.domain.fileService.service.FileService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {
    private final FileService fileService;

    @Operation(
            summary = "파일 저장",
            description = """
                    ### 파일 저장 API
                    
                    파일을 저장합니다.
                    response : file Url 
                    """
    )
    @PostMapping("/upload")
    public ApiResponse<FileResDto> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String uploadedUrl = fileService.uploadFile(file);

        FileResDto response = FileResDto.builder()
                .fileId(uploadedUrl)
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
