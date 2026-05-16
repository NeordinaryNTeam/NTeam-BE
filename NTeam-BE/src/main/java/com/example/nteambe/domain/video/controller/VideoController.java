package com.example.nteambe.domain.video.controller;

import com.example.nteambe.domain.video.dto.request.SaveVideoReqDto;
import com.example.nteambe.domain.video.dto.response.VideoResDto;
import com.example.nteambe.domain.video.exception.VideoErrorCode;
import com.example.nteambe.domain.video.exception.VideoSuccessCode;
import com.example.nteambe.domain.video.service.SpotVideoService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
    private final SpotVideoService spotVideoService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(VideoErrorCode.VIDEO_BAD_REQUEST, null));
    }

    @Operation(
            summary = "영상 상세 정보 조회",
            description = """
                    ### 영상 상세 조회 API
                    
                    영상 상세 정보를 조회합니다. (List)  
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "SPOT201_1 - 성공적으로 스팟이 생성되었습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "SPOT400_2 - 스팟 생성에 필요한 필수 입력 항목이 누락되었거나 데이터 형식이 올바르지 않습니다.")
    })
    @GetMapping()
    public ApiResponse<List<VideoResDto>> getVideos(
            @RequestAttribute Long userId,

            @NotBlank(message = "deviceToken은 필수입니다.")
            @Size(max = 255, message = "deviceToken 길이 초과")
            @RequestHeader
            String deviceToken,

            @RequestParam Long spotId
    ) {
        List<VideoResDto> result = spotVideoService.getVideos(spotId);
        return ApiResponse.onSuccess(VideoSuccessCode.VIDEO_LIST, result);
    }

    @Operation(
            summary = "영상 상세 정보 저장 (파일 저장 후 요청)",
            description = """
                    ### 영상 상세 정보 저장 API
                    
                    영상을 저장합니다.
                    파일 업로드 후 응답으로 받은 url을 포함하여 요청해주시면 됩니다.  
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "VIDEO201_1 - 성공적으로 스팟이 생성되었습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "VIDEO400_1 - 스팟 생성에 필요한 필수 입력 항목이 누락되었습니다.")
    })
    @PostMapping()
    public ApiResponse<VideoResDto> postSaveVideo(
            @RequestAttribute Long userId,

            @NotBlank(message = "deviceToken은 필수입니다.")
            @Size(max = 255, message = "deviceToken 길이 초과")
            @RequestHeader
            String deviceToken,

            @RequestBody
            @Valid
            SaveVideoReqDto dto
    ) {
        VideoResDto result = spotVideoService.createVideo(dto, userId);
        return ApiResponse.onSuccess(VideoSuccessCode.VIDEO_CREATE, result);
    }
}
