package com.example.nteambe.domain.spot.repository;

import com.example.nteambe.domain.spot.entity.SavedSpot;
import com.example.nteambe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedSpotRepository extends JpaRepository<SavedSpot, Long> {
    List<SavedSpot> findByUser(User user);
}
