package com.example.nteambe.domain.spot.entity;

import com.example.nteambe.domain.spot.enums.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spot_status_list")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotStatusList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_status_list_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "spot_status", joinColumns = @JoinColumn(name = "spot_status_list_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "status_name")
    @Builder.Default
    private Set<StatusType> statuses = new HashSet<>();
}
