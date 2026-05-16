package com.example.nteambe.domain.spot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SpotSummaryResDTO {
    private Long spotId;
    private String name;
    private String latitude;
    private String longitude;
    private String mainAddress;
    private String subAddress;
    private String difficulty;
    private String description;
    private List<String> features;
    private List<List<String>> statusList;
}