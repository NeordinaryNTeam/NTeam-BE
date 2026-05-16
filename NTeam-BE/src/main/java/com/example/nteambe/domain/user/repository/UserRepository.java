package com.example.nteambe.domain.user.repository;

import com.example.nteambe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByToken(String token);
}
