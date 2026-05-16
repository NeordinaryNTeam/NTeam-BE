package com.example.nteambe.domain.user.dto.request;

public record SignUpReqDto(
        String deviceToken,
        String nickName
) {}