package com.example.nteambe.domain.spot.entity;

import com.example.nteambe.domain.spot.enums.DifficultyType;
import com.example.nteambe.domain.spot.enums.FeatureType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private DifficultyType difficulty;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "caption_img_url")
    private String captionImgUrl;

    // 스팟 특징
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "spot_feature", joinColumns = @JoinColumn(name = "spot_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "feature_name")
    @Builder.Default
    private Set<FeatureType> features = new HashSet<>();

    @OneToMany(mappedBy = "spot", fetch = FetchType.LAZY)
    @BatchSize(size = 50)
    @Builder.Default
    private List<SpotStatusList> statusList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
