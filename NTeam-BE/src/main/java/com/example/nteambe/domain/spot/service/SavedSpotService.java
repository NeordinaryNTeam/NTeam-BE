package com.example.nteambe.domain.spot.service;

import com.example.nteambe.domain.spot.dto.CreateSavedSpotResDTO;
import com.example.nteambe.domain.spot.entity.SavedSpot;
import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.exception.code.SpotErrorCode;
import com.example.nteambe.domain.spot.repository.SavedSpotRepository;
import com.example.nteambe.domain.spot.repository.SpotRepository;
import com.example.nteambe.domain.user.entity.User;
import com.example.nteambe.domain.user.repository.UserRepository;
import com.example.nteambe.global.apiPayload.code.GeneralErrorCode;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavedSpotService {

    private final SavedSpotRepository savedSpotRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;

    public List<SavedSpot> getSavedSpots(Long userId, String deviceToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.UNAUTHORIZED));
        return savedSpotRepository.findByUser(user);
    }

    @Transactional
    public CreateSavedSpotResDTO createSavedSpot(Long userId, Long spotId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.UNAUTHORIZED));
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new ProjectException(SpotErrorCode.SPOT_NOT_FOUND));

        if (savedSpotRepository.existsByUserAndSpot(user, spot)) {
            throw new ProjectException(SpotErrorCode.SAVED_SPOT_ALREADY_SAVED);
        }

        SavedSpot savedSpot = SavedSpot.builder()
                .user(user)
                .spot(spot)
                .build();

        SavedSpot saved = savedSpotRepository.save(savedSpot);

        return CreateSavedSpotResDTO.builder()
                .savedSpotId(saved.getId())
                .build();
    }
}