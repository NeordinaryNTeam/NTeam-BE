package com.example.nteambe.domain.video.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveVideoReqDto(
        @NotNull Long spotId,
        @NotBlank String title,
        @NotBlank String videoUrl
) {}