package com.example.nteambe.domain.spot.exception.code;

import com.example.nteambe.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SpotErrorCode implements BaseErrorCode {
    SPOT_FILTER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "SPOT400_1", "잘못된 필터 조건 요청입니다."),
    SPOT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "SPOT400_3", "필수 입력 항목이 누락되었거나 데이터 형식이 올바르지 않습니다."),
    SPOT_NOT_FOUND(HttpStatus.NOT_FOUND, "SPOT404_1", "해당 스팟을 찾을 수 없습니다."),
    SAVED_SPOT_ALREADY_SAVED(HttpStatus.CONFLICT, "SAVED_SPOT409_1", "이미 저장된 스팟입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
