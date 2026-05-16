package com.example.nteambe.domain.spot.service;

import com.example.nteambe.domain.spot.dto.request.SaveSpotStatusListReqDto;
import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.entity.SpotStatusList;
import com.example.nteambe.domain.spot.enums.StatusType;
import com.example.nteambe.domain.spot.exception.code.SpotErrorCode;
import com.example.nteambe.domain.spot.repository.SpotRepository;
import com.example.nteambe.domain.spot.repository.SpotStatusListRepository;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotStatusListService {

    private final SpotStatusListRepository spotStatusListRepository;
    private final SpotRepository spotRepository;

    @Transactional
    public List<StatusType> createSpotStatus(Long spotId, SaveSpotStatusListReqDto dto) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new ProjectException(SpotErrorCode.SPOT_NOT_FOUND));

        SpotStatusList statusList = SpotStatusList.builder()
                .spot(spot)
                .statuses(dto.statuses() != null ? new HashSet<>(dto.statuses()) : new HashSet<>())
                .build();

        SpotStatusList saved = spotStatusListRepository.save(statusList);

        return new ArrayList<>(saved.getStatuses());
    }
}
