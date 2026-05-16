package com.example.nteambe.domain.fileService.exception.code;

import com.example.nteambe.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileErrorCode implements BaseErrorCode {

    FILE_EMPTY(HttpStatus.BAD_REQUEST, "FILE400_1", "파일이 비어 있습니다."),
    FILE_INVALID_TYPE(HttpStatus.BAD_REQUEST, "FILE400_2", "이미지 또는 영상 파일만 업로드 가능합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
