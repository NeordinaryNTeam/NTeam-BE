package com.example.nteambe.domain.spot.dto.response;

import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record SpotResDto(
        Long spotId,
        String name,
        List<FeatureType> distinct,
        String latitude,
        String longitude,
        String mainAddress,
        String subAddress,
        DifficultyType difficulty,
        OffsetDateTime createdAt
) {
}
