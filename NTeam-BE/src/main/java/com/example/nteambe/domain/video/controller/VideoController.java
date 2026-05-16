package com.example.nteambe.domain.video.controller;

import com.example.nteambe.domain.video.dto.request.SaveVideoReqDto;
import com.example.nteambe.domain.video.dto.response.VideoResDto;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

    @Operation(
            summary = "영상 조회",
            description = """
                    ### 영상 조회 API
                    
                    영상을 조회합니다. (List)  
                    """
    )
    @GetMapping()
    public ApiResponse<List<VideoResDto>> getVideos(
            @RequestParam() Long spotId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @Operation(
            summary = "영상 저장 (파일 저장 후 요청)",
            description = """
                    ### 영상 저장 API
                    
                    영상을 저장합니다.
                    파일 업로드 후 응답으로 받은 url을 포함하여 요청해주시면 됩니다.  
                    """
    )
    @PostMapping()
    public ApiResponse<VideoResDto> postSaveVideo(
            @RequestBody SaveVideoReqDto dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
