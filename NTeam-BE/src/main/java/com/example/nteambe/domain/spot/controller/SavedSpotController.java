package com.example.nteambe.domain.spot.controller;

import com.example.nteambe.domain.spot.converter.SavedSpotConverter;
import com.example.nteambe.domain.spot.dto.CreateSavedSpotReqDTO;
import com.example.nteambe.domain.spot.dto.CreateSavedSpotResDTO;
import com.example.nteambe.domain.spot.dto.SavedSpotListResDTO;
import com.example.nteambe.domain.spot.exception.code.SpotSuccessCode;
import com.example.nteambe.domain.spot.service.SavedSpotService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saved-spots")
public class SavedSpotController {

    private final SavedSpotService savedSpotService;

    @Operation(
            summary = "Saved Spot 조회",
            description = "저장된 Spot 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "SAVED_SPOT200_1 - 성공적으로 저장된 스팟 목록을 조회했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "COMMON401_1 - 인증되지 않았습니다.")
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<SavedSpotListResDTO>>> getSavedSpots(
            @RequestAttribute Long userId,
            @RequestHeader String deviceToken
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                SpotSuccessCode.SAVED_SPOT_LIST,
                SavedSpotConverter.toSavedSpotListResDTOs(savedSpotService.getSavedSpots(userId, deviceToken))
        ));
    }

    @Operation(summary="Saved Spot 추가",
            description = "스팟을 저장 목록에 추가합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "SAVED_SPOT201_1 - 성공적으로 스팟을 저장했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "COMMON401_1 - 인증되지 않았습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "SPOT404_1 - 존재하지 않는 스팟은 저장할 수 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "SAVED_SPOT409_1 - 이미 저장된 스팟입니다.")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<CreateSavedSpotResDTO>> createSavedSpot(
            @RequestAttribute Long userId,
            @RequestHeader String deviceToken,
            @RequestBody CreateSavedSpotReqDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(
                        SpotSuccessCode.SAVED_SPOT_CREATE,
                        savedSpotService.createSavedSpot(userId, request.getSpotId())
                ));
    }
}