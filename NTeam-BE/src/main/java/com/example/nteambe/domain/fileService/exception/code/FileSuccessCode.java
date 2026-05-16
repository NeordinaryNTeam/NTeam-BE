package com.example.nteambe.domain.fileService.exception.code;

import com.example.nteambe.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileSuccessCode implements BaseSuccessCode {

    FILE_UPLOAD(HttpStatus.OK, "FILE200_1", "파일이 성공적으로 업로드되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
