package com.example.nteambe.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignUpReqDto(
        @NotBlank String deviceToken,
        @NotBlank String nickName
) {}