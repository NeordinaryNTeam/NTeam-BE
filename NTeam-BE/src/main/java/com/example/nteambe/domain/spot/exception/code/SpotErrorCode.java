package com.example.nteambe.domain.spot.exception.code;

import com.example.nteambe.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SpotErrorCode implements BaseErrorCode {
    SPOT_NOT_FOUND(HttpStatus.NOT_FOUND, "SPOT404_1", "존재하지 않는 스팟은 저장할 수 없습니다."),
    SAVED_SPOT_ALREADY_SAVED(HttpStatus.CONFLICT, "SAVED_SPOT409_1", "이미 저장된 스팟입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
