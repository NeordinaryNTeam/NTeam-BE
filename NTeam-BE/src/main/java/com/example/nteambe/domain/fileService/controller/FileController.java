package com.example.nteambe.domain.fileService.controller;

import com.example.nteambe.domain.fileService.dto.response.FileResDto;
import com.example.nteambe.domain.fileService.exception.code.FileSuccessCode;
import com.example.nteambe.domain.fileService.service.FileService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "COMMON200_1 - 성공적으로 요청을 처리하였습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "FILE400_1 - 파일이 비어 있습니다. / FILE400_2 - 이미지 또는 영상 파일만 업로드 가능합니다.")
    })
    @PostMapping("/upload")
    public ApiResponse<FileResDto> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String uploadedUrl = fileService.uploadFile(file);

        FileResDto response = FileResDto.builder()
                .fileId(uploadedUrl)
                .build();

        return ApiResponse.onSuccess(FileSuccessCode.FILE_UPLOAD, response);
    }


    @Operation(
            summary = "파일 조회",
            description = """
                    ### 파일 조회 API

                    파일을 조회합니다.
                    response : file Url
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "200 - 파일 스트림 반환"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "COMMON500_1 - S3에서 파일을 찾을 수 없거나 서버 오류입니다.")
    })
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(
            @RequestParam String url
    ) {
        return fileService.viewFile(url);
    }
}
