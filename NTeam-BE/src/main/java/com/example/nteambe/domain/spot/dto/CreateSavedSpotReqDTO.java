package com.example.nteambe.domain.spot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateSavedSpotReqDTO {
    @NotNull
    @JsonProperty("spot_id")
    private Long spotId;
}