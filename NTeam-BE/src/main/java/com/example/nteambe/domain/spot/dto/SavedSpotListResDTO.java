package com.example.nteambe.domain.spot.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SavedSpotListResDTO {
    private Long savedSpotId;
    private SpotSummaryResDTO spot;
}