package com.example.nteambe.domain.spot.repository;

import com.example.nteambe.domain.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot,Long> {
}
