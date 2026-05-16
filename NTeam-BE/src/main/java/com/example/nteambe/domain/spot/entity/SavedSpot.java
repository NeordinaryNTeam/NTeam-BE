package com.example.nteambe.domain.spot.entity;

import com.example.nteambe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saved_spot")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saved_spot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;
}