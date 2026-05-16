package com.example.nteambe.domain.user.controller;

import com.example.nteambe.domain.user.dto.request.SignUpReqDto;
import com.example.nteambe.domain.user.service.UserService;
import com.example.nteambe.global.apiPayload.ApiResponse;
import com.example.nteambe.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ) throws Exception {
        Long result = userService.saveUser(dto);

        if (result == null) {
            throw new Exception("유저 저장 실패");
        }

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "요청을 성공적으로 처리하였습니다.");
    }
}
