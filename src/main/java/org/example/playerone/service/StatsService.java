package org.example.playerone.service;

import org.example.playerone.dto.StatsRequest;
import org.example.playerone.exception.ResourceNotFoundException;
import org.example.playerone.model.Player;
import org.example.playerone.model.Stats;
import org.example.playerone.repository.PlayerRepository;
import org.example.playerone.repository.StatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository statsRepository;
    private final PlayerRepository playerRepository;

    public StatsService(StatsRepository statsRepository, PlayerRepository playerRepository) {
        this.statsRepository = statsRepository;
        this.playerRepository = playerRepository;
    }

    public Stats saveStats(StatsRequest request) {
        Player player = playerRepository.findById(request.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + request.getPlayerId()));

        Stats stats = new Stats();
        stats.setPlayer(player);
        stats.setMatchDate(request.getMatchDate());
        stats.setWinStatus(request.getWinStatus());
        stats.setKdaRatio(request.getKdaRatio());
        stats.setScore(request.getScore());
        return statsRepository.save(stats);
    }

    public List<Stats> findAllStats() {
        return statsRepository.findAll();
    }

    public List<Stats> findStatsByPlayerId(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new ResourceNotFoundException("Player not found with ID: " + playerId);
        }
        return statsRepository.findByPlayer_Id(playerId);
    }
}
