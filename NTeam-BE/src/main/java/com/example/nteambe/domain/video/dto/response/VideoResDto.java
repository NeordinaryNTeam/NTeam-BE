package com.example.nteambe.domain.video.dto.response;


import java.time.OffsetDateTime;

public record VideoResDto(
        Long videoId,
        String videoUrl,
        String title,
        OffsetDateTime createdAt,
        Long userId,
        String nickname
) {
}