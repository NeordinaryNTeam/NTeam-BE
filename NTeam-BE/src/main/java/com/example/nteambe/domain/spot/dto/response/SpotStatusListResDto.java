package com.example.nteambe.domain.spot.dto.response;

import com.example.nteambe.domain.spot.enums.StatusType;
import lombok.Builder;

import java.util.List;

@Builder
public record SpotStatusListResDto(
        Long spotStatusListId,
        List<StatusType> statuses
) {}
