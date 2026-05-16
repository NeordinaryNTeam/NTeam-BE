package com.example.nteambe.domain.spot.dto.request;

import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import com.example.nteambe.domain.spot.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaveSpotReqDto(
        @NotBlank String name,
        @NotBlank String captionImgUrl,
        @NotBlank String latitude,
        @NotBlank String longitude,
        @NotBlank @JsonProperty("main_address") String mainAddress,
        @NotBlank @JsonProperty("sub_address") String subAddress,
        @NotNull DifficultyType difficulty,
        String description,
        @NotEmpty List<FeatureType> features,
        List<StatusType> statuses
) {
}