package com.example.nteambe.domain.user.dto.response;

import lombok.Builder;

@Builder
public record GetUserNameResDto(
        String nickName
) {

}
