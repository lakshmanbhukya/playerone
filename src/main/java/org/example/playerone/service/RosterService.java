package org.example.playerone.service;

import jakarta.transaction.Transactional;
import org.example.playerone.enums.PlayerStatus;
import org.example.playerone.exception.ResourceNotFoundException;
import org.example.playerone.model.Player;
import org.example.playerone.model.Team;
import org.example.playerone.repository.PlayerRepository;
import org.example.playerone.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class RosterService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public RosterService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Player AssignPlayerToTeam(Long playerId, Long teamId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + playerId));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + teamId));
        player.setTeam(team);
        player.setStatus(PlayerStatus.ACTIVE);
        return playerRepository.save(player);
    }

    @Transactional
    public Player UnassignPlayerFromTeam(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + playerId));
        player.setTeam(null);
        player.setStatus(PlayerStatus.FREE_AGENT);
        return playerRepository.save(player);
    }
}
