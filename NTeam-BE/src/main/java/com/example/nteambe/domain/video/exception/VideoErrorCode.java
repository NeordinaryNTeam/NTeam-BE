package com.example.nteambe.domain.video.exception;

import com.example.nteambe.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VideoErrorCode implements BaseErrorCode {

    VIDEO_BAD_REQUEST(HttpStatus.BAD_REQUEST, "VIDEO400_1", "영상 등록 정보가 누락되었습니다."),
    VIDEO_NOT_FOUND(HttpStatus.NOT_FOUND, "VIDEO404_1", "영상을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
