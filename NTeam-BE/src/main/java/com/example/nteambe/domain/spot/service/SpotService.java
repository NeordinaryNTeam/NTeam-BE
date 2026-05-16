package com.example.nteambe.domain.spot.service;

import com.example.nteambe.domain.spot.dto.request.SaveSpotReqDto;
import com.example.nteambe.domain.spot.dto.response.SaveSpotResDto;
import com.example.nteambe.domain.spot.dto.response.SpotResDto;
import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import com.example.nteambe.domain.spot.enums.StatusType;
import com.example.nteambe.domain.spot.exception.code.SpotErrorCode;
import com.example.nteambe.domain.spot.repository.SpotRepository;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotService {
    private final SpotRepository spotRepository;

    @Transactional(readOnly = true)
    public List<SpotResDto> getSpots(
            String mainAddress, String subAddress,
            DifficultyType difficulty, List<FeatureType> features,
            List<StatusType> statuses, Long spotId) {

        List<Spot> spots = spotRepository.findByFilters(mainAddress, subAddress, difficulty, spotId);

        if (features != null && !features.isEmpty()) {
            HashSet<FeatureType> featureSet = new HashSet<>(features);
            spots = spots.stream()
                    .filter(s -> new HashSet<>(s.getFeatures()).containsAll(featureSet))
                    .collect(Collectors.toList());
        }

        if (statuses != null && !statuses.isEmpty()) {
            HashSet<StatusType> statusSet = new HashSet<>(statuses);
            spots = spots.stream()
                    .filter(s -> {
                        Set<StatusType> allStatuses = s.getStatusList().stream()
                                .flatMap(sl -> sl.getStatuses().stream())
                                .collect(Collectors.toSet());
                        return allStatuses.containsAll(statusSet);
                    })
                    .collect(Collectors.toList());
        }

        if (spotId != null && spots.isEmpty()) {
            throw new ProjectException(SpotErrorCode.SPOT_NOT_FOUND);
        }

        return spots.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public SaveSpotResDto createSpot(SaveSpotReqDto dto) {
        Spot spot = Spot.builder()
                .name(dto.name())
                .captionImgUrl(dto.captionImgUrl())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .mainAddress(dto.mainAddress())
                .subAddress(dto.subAddress())
                .difficulty(dto.difficulty())
                .description(dto.description())
                .features(dto.features() != null ? new HashSet<>(dto.features()) : new HashSet<>())
                .build();
        Spot saved = spotRepository.save(spot);
        return new SaveSpotResDto(saved.getId());
    }

    private SpotResDto toDto(Spot spot) {
        List<List<StatusType>> statusListDtos = spot.getStatusList().stream()
                .map(sl -> new ArrayList<>(sl.getStatuses()))
                .collect(Collectors.toList());

        return SpotResDto.builder()
                .spotId(spot.getId())
                .name(spot.getName())
                .captionImgUrl(spot.getCaptionImgUrl())
                .latitude(spot.getLatitude())
                .longitude(spot.getLongitude())
                .mainAddress(spot.getMainAddress())
                .subAddress(spot.getSubAddress())
                .difficulty(spot.getDifficulty())
                .description(spot.getDescription())
                .features(new ArrayList<>(spot.getFeatures()))
                .statusList(statusListDtos)
                .createdAt(spot.getCreatedAt())
                .build();
    }
}
