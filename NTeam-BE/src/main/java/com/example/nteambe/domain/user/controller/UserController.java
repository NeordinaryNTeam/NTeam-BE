package com.example.nteambe.domain.user.controller;

import com.example.nteambe.domain.user.dto.request.SignUpReqDto;
import com.example.nteambe.domain.user.dto.response.GetUserNameResDto;
import com.example.nteambe.domain.user.service.UserService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "회원가입 (유저 등록 처리, 앱에서 구분 가능한 값 담아서 전달)",
            description = """
                    ### 회원가입
                    
                    회원 가입을 처리합니다.
                    닉네임과 디바이스 고유 값 전달해주세요.  
                    """
    )
    @PostMapping("/sign-up")
    public ApiResponse<String> postSignUp(
            @RequestBody SignUpReqDto dto
    ) {
        userService.saveUser(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "요청을 성공적으로 처리하였습니다.");
    }

    @Operation(
            summary = "유저 닉네임 조회 (유저 등록 여부 확인 API)",
            description = """
                    ### 유저 등록 여부 확인용으로 사용
                    
                    - 유저를 찾을 수 없습니다 에러 발생 시, 회원가입 이력이 없는거에요 !
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "COMMON200_1 - 성공적으로 요청을 처리했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "COMMON404_2 - 유저를 찾을 수 없습니다.")
    })
    @GetMapping("/me")
    public ApiResponse<GetUserNameResDto> getUserName(
            @RequestAttribute Long userId,

            @NotBlank(message = "deviceToken은 필수입니다.")
            @Size(max = 255, message = "deviceToken 길이 초과")
            @RequestHeader
            String deviceToken
    ) {
        String userName = userService.getUserNameByToken(deviceToken);

        GetUserNameResDto response = GetUserNameResDto.builder()
                .nickName(userName)
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
