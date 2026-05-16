package com.example.nteambe.domain.user.service;

import com.example.nteambe.domain.user.dto.request.SignUpReqDto;
import com.example.nteambe.domain.user.repository.UserRepository;
import com.example.nteambe.global.apiPayload.code.GeneralErrorCode;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.nteambe.domain.user.entity.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(SignUpReqDto dto) {
        return userRepository.findFirstByToken(dto.deviceToken())
                .map(User::getId)
                .orElseGet(() -> {
                    User user = User.builder()
                            .nickname(dto.nickName())
                            .token(dto.deviceToken())
                            .build();
                    return userRepository.save(user).getId();
                });
    }

    public Long getUserIdByToken(String token) {

        User user = userRepository.findFirstByToken(token)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.USER_NOT_FOUND));

        return user.getId();
    }

    public String getUserNameByToken(String token) {

        User user = userRepository.findFirstByToken(token)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.USER_NOT_FOUND));

        return user.getNickname();
    }
}