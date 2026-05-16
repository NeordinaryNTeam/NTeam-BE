package com.example.nteambe.domain.video.repository;

import com.example.nteambe.domain.video.entity.SpotVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpotVideoRepository extends JpaRepository<SpotVideo,Long> {
    List<SpotVideo> findAllBySpotIdOrderByCreatedAtDesc(Long spotId);
}
