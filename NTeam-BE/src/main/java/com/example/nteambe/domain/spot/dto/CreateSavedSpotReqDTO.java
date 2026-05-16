package com.example.nteambe.domain.spot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateSavedSpotReqDTO {
    @JsonProperty("spot_id")
    private Long spotId;




}