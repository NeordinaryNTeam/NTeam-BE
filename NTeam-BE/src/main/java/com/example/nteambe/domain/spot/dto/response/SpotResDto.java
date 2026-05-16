package com.example.nteambe.domain.spot.dto.response;

import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record SpotResDto(
        Long spotId,
        String name,
        String captionImgUrl,
        String latitude,
        String longitude,
        String mainAddress,
        String subAddress,
        DifficultyType difficulty,
        List<FeatureType> features,
        List<SpotStatusListResDto> statusList,
        LocalDateTime createdAt
) {
}
