package com.example.nteambe.domain.spot.repository;

import com.example.nteambe.domain.spot.entity.Spot;
import com.example.nteambe.domain.spot.enums.DifficultyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query("SELECT DISTINCT s FROM Spot s " +
           "LEFT JOIN FETCH s.features " +
           "LEFT JOIN FETCH s.statuses " +
           "WHERE (:mainAddress IS NULL OR s.mainAddress = :mainAddress) " +
           "AND (:subAddress IS NULL OR s.subAddress = :subAddress) " +
           "AND (:difficulty IS NULL OR s.difficulty = :difficulty) " +
           "AND (:spotId IS NULL OR s.id = :spotId)")
    List<Spot> findByFilters(
            @Param("mainAddress") String mainAddress,
            @Param("subAddress") String subAddress,
            @Param("difficulty") DifficultyType difficulty,
            @Param("spotId") Long spotId
    );
}