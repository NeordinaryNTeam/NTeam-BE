package com.example.nteambe.domain.video.dto.response;


import lombok.Builder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Builder
public record VideoResDto(
        Long videoId,
        String url,
        String title,
        LocalDateTime createdAt,
        String createdBy
) {
}