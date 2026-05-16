package com.example.nteambe.domain.spot.service;

import com.example.nteambe.domain.spot.dto.CreateSavedSpotResDTO;
import com.example.nteambe.domain.spot.entity.SavedSpot;
import com.example.nteambe.domain.spot.repository.SavedSpotRepository;
import com.example.nteambe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavedSpotService {

    private final SavedSpotRepository savedSpotRepository;
    private final UserRepository userRepository;

    public List<SavedSpot> getSavedSpots() {
        return List.of();
    }

    @Transactional
    public CreateSavedSpotResDTO createSavedSpot(Long spotId) {
        return CreateSavedSpotResDTO.builder()
                .savedSpotId(0L)
                .build();
    }
}