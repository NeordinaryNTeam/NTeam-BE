package com.example.nteambe.domain.user.repository;

import com.example.nteambe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
