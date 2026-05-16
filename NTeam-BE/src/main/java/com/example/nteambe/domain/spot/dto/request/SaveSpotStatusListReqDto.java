package com.example.nteambe.domain.spot.dto.request;

import com.example.nteambe.domain.spot.enums.StatusType;

import java.util.List;

public record SaveSpotStatusListReqDto(
        List<StatusType> statuses
) {}
