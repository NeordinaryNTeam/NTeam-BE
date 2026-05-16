package com.example.nteambe.domain.spot.entity;

import com.example.nteambe.domain.spot.enums.Difficulty;
import com.example.nteambe.domain.spot.enums.Feature;
import com.example.nteambe.domain.spot.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="spot")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(name = "main_address")
    private String mainAddress;

    @Column(name = "sub_address")
    private String subAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 스팟 특징
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "spot_feature", joinColumns = @JoinColumn(name = "spot_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "feature_name")
    @Builder.Default
    private List<Feature> features = new ArrayList<>();

    // 스팟 상태
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "spot_status", joinColumns = @JoinColumn(name = "spot_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "status_name")
    @Builder.Default
    private List<Status> statuses = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
