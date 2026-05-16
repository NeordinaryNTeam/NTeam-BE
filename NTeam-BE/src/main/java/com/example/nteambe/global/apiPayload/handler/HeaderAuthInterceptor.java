package com.example.nteambe.global.apiPayload.handler;

import com.example.nteambe.domain.user.service.UserService;
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
            throw new Exception("유저를 찾을 수 없습니다.");
        }

        request.setAttribute("userId", userId);

        return true;
    }
}