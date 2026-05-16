package com.example.nteambe.domain.video.dto.request;

public record SaveVideoReqDto(
        Long spotId,
        String title,
        String videoUrl
) {}