package com.example.nteambe.domain.spot.repository;

import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.entity.SpotStatusList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotStatusListRepository extends JpaRepository<SpotStatusList, Long> {
    List<SpotStatusList> findBySpot(Spot spot);
}
