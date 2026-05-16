package com.example.nteambe.domain.video.service;

import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.repository.SpotRepository;
import com.example.nteambe.domain.user.entity.User;
import com.example.nteambe.domain.user.repository.UserRepository;
import com.example.nteambe.domain.video.dto.request.SaveVideoReqDto;
import com.example.nteambe.domain.video.dto.response.VideoResDto;
import com.example.nteambe.domain.video.entity.SpotVideo;
import com.example.nteambe.domain.spot.exception.code.SpotErrorCode;
import com.example.nteambe.domain.video.repository.SpotVideoRepository;
import com.example.nteambe.global.apiPayload.code.GeneralErrorCode;
import com.example.nteambe.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotVideoService {
    private final SpotVideoRepository spotVideoRepository;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;

    public List<VideoResDto> getVideos(Long spotId) {
        spotRepository.findById(spotId)
                .orElseThrow(() -> new ProjectException(SpotErrorCode.SPOT_NOT_FOUND));

        List<SpotVideo> videos = spotVideoRepository.findAllBySpotIdOrderByCreatedAtDesc(spotId);

        return videos.stream()
                .map(SpotVideoService::convertVideoToDto)
                .toList();
    }

    @Transactional
    public VideoResDto createVideo(
            SaveVideoReqDto request,
            Long userId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.UNAUTHORIZED));

        Spot spot = spotRepository.findById(request.spotId())
                .orElseThrow(() -> new ProjectException(SpotErrorCode.SPOT_NOT_FOUND));

        SpotVideo spotVideo = SpotVideo.builder()
                .title(request.title())
                .url(request.videoUrl())
                .user(user)
                .spot(spot)
                .build();

        SpotVideo savedVideo = spotVideoRepository.save(spotVideo);

        return convertVideoToDto(savedVideo);
    }

    private static VideoResDto convertVideoToDto(SpotVideo video) {

        return VideoResDto.builder()
                .videoId(video.getId())
                .title(video.getTitle())
                .url(video.getUrl())
                .createdBy(video.getUser().getNickname())
                .createdAt(video.getCreatedAt())
                .build();
    }
}
