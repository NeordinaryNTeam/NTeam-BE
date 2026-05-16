package com.example.nteambe.domain.spot.converter;

import com.example.nteambe.domain.spot.dto.SavedSpotListResDTO;
import com.example.nteambe.domain.spot.dto.SpotSummaryResDTO;
import com.example.nteambe.domain.spot.entity.SavedSpot;
import com.example.nteambe.domain.spot.entity.Spot;

import java.util.List;
import java.util.stream.Collectors;

public class SavedSpotConverter {

    public static SpotSummaryResDTO toSpotSummaryResDTO(Spot spot) {
        return SpotSummaryResDTO.builder()
                .spotId(spot.getId())
                .name(spot.getName())
                .mainAddress(spot.getMainAddress())
                .subAddress(spot.getSubAddress())
                .difficulty(spot.getDifficulty().name())
                .description(spot.getDescription())
                .features(spot.getFeatures().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .statuses(spot.getStatuses().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .build();
    }

    public static SavedSpotListResDTO toSavedSpotListResDTO(SavedSpot savedSpot) {
        return SavedSpotListResDTO.builder()
                .savedSpotId(savedSpot.getSavedSpotId())
                .spot(toSpotSummaryResDTO(savedSpot.getSpot()))
                .build();
    }

    public static List<SavedSpotListResDTO> toSavedSpotListResDTOs(List<SavedSpot> savedSpots) {
        return savedSpots.stream()
                .map(SavedSpotConverter::toSavedSpotListResDTO)
                .collect(Collectors.toList());
    }
}