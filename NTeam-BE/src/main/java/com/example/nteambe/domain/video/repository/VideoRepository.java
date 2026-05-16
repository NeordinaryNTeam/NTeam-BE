package com.example.nteambe.domain.video.repository;

import com.example.nteambe.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
