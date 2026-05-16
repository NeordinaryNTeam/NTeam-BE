package com.example.nteambe.domain.spot.controller;

import com.example.nteambe.domain.spot.dto.request.SaveSpotReqDto;
import com.example.nteambe.domain.spot.dto.response.SaveSpotResDto;
import com.example.nteambe.domain.spot.dto.response.SpotResDto;
import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {

    @Operation(
            summary = "Spot 조회",
            description = """
                    ### Spot 조회 API
                    
                    Spot을 조회합니다. 
                    """
    )
    @GetMapping()
    public ApiResponse<List<SpotResDto>> getSpots(
            @RequestAttribute Long userId,
            @RequestHeader String deviceToken,
            @RequestParam String mainAddress,
            @RequestParam String subAddress,
            @RequestParam DifficultyType difficulty,
            @RequestParam FeatureType feature,
            @RequestParam(required = false) Long spotId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Operation(
            summary = "Spot 저장",
            description = """
                    ### Spot 저장 API
                    
                    Spot을 저장합니다. 
                    """
    )
    @PostMapping()
    public ApiResponse<SaveSpotResDto> postSaveSpot(
            @RequestBody SaveSpotReqDto dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
