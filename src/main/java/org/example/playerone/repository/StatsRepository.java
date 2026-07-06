package org.example.playerone.repository;

import org.example.playerone.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats,Long> {
    List<Stats> findByPlayer_Id(Long playerId);
}
