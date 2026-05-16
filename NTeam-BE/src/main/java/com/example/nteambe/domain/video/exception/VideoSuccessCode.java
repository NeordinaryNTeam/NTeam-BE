package com.example.nteambe.domain.video.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VideoSuccessCode implements com.example.nteambe.global.apiPayload.code.BaseSuccessCode {

    VIDEO_LIST(HttpStatus.OK, "VIDEO200_1", "성공적으로 영상 목록을 조회했습니다."),
    VIDEO_CREATE(HttpStatus.OK, "VIDEO201_1", "성공적으로 영상을 저장했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
