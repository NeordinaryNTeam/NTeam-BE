package com.example.nteambe.domain.spot.controller;

import com.example.nteambe.domain.spot.dto.request.SaveSpotReqDto;
import com.example.nteambe.domain.spot.dto.response.SaveSpotResDto;
import com.example.nteambe.domain.spot.dto.response.SpotResDto;
import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import com.example.nteambe.domain.spot.enums.StatusType;
import com.example.nteambe.domain.spot.exception.code.SpotErrorCode;
import com.example.nteambe.domain.spot.exception.code.SpotSuccessCode;
import com.example.nteambe.domain.spot.service.SpotService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(SpotErrorCode.SPOT_BAD_REQUEST, null));
    }

    @Operation(
            summary = "Spot 조회",
            description = """
                    ### Spot 조회 API
                    
                    Spot을 조회합니다. 
                    """
    )
    @GetMapping()
    public ApiResponse<List<SpotResDto>> getSpots(
            @RequestParam(required = false) String mainAddress,
            @RequestParam(required = false) String subAddress,
            @RequestParam(required = false) DifficultyType difficulty,
            @RequestParam(required = false) List<FeatureType> features,
            @RequestParam(required = false) List<StatusType> statuses,
            @RequestParam(required = false) Long spotId
    ) {
        return ApiResponse.onSuccess(SpotSuccessCode.SPOT_LIST,
                spotService.getSpots(mainAddress, subAddress, difficulty, features, statuses, spotId));
    }

    @Operation(
            summary = "Spot 저장",
            description = """
                    ### Spot 저장 API
                    
                    Spot을 저장합니다. 
                    """
    )
    @PostMapping()
    public ResponseEntity<ApiResponse<SaveSpotResDto>> postSaveSpot(
            @RequestBody @Valid SaveSpotReqDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(SpotSuccessCode.SPOT_CREATE, spotService.createSpot(dto)));
    }
}