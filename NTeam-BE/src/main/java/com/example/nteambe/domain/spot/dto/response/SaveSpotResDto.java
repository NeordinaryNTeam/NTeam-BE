package com.example.nteambe.domain.spot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SaveSpotResDto(
        @JsonProperty("spot_id") Long spotId
) {
}