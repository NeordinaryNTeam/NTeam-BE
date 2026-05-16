package com.example.nteambe.domain.spot.converter;

import com.example.nteambe.domain.spot.dto.CreateSavedSpotReqDTO;
import com.example.nteambe.domain.spot.entity.Spot;

public class SpotConverter {
    public static Spot toEntity(CreateSavedSpotReqDTO dto) {
        return Spot.builder()
                .id(dto.getSpotId())
                .build();
    }
}