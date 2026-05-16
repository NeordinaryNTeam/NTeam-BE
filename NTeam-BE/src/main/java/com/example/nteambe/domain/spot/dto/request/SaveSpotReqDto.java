package com.example.nteambe.domain.spot.dto.request;

import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;

import java.time.OffsetDateTime;
import java.util.List;

public record SaveSpotReqDto (
        Long spotId,
        String name,

        String captionImgUrl,

        List<FeatureType> distinct,
        String latitude,
        String longitude,
        String mainAddress,
        String subAddress,
        DifficultyType difficulty,
        OffsetDateTime createdAt
) {
}
