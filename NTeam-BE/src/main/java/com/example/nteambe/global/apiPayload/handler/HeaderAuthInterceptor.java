package com.example.nteambe.global.apiPayload.handler;

import com.example.nteambe.domain.user.service.UserService;
import com.example.nteambe.global.apiPayload.code.GeneralErrorCode;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class HeaderAuthInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("deviceToken");

        Long userId = userService.getUserIdByToken(token);

        if (userId == null) {
            throw new ProjectException(GeneralErrorCode.UNAUTHORIZED);
        }

        request.setAttribute("userId", userId);

        return true;
    }
}