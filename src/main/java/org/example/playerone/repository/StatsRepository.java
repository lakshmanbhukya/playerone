package org.example.playerone.repository;

import org.example.playerone.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats,Long> {
    List<Stats> findByPlayerId(Long playerId);
}
