package com.example.nteambe.domain.spot.exception.code;

import com.example.nteambe.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SpotSuccessCode implements BaseSuccessCode {
    SPOT_LIST(HttpStatus.OK, "SPOT200_1", "성공적으로 스팟 목록을 조회했습니다."),
    SPOT_CREATE(HttpStatus.CREATED, "SPOT201_1", "스팟이 성공적으로 생성되었습니다."),
    SAVED_SPOT_LIST(HttpStatus.OK, "SAVED_SPOT200_1", "성공적으로 저장된 스팟 목록을 조회했습니다."),
    SAVED_SPOT_CREATE(HttpStatus.CREATED, "SAVED_SPOT201_1", "성공적으로 스팟을 저장했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
