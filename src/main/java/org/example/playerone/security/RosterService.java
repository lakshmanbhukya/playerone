package org.example.playerone.security;

import jakarta.transaction.Transactional;
import org.example.playerone.enums.PlayerStatus;
import org.example.playerone.model.Player;
import org.example.playerone.model.Team;
import org.example.playerone.repository.PlayerRepository;
import org.example.playerone.repository.TeamRepository;

public class RosterService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public RosterService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Player AssignPlayerToTeam(Long playerId,Long teamId){
        Player player=playerRepository.findById(playerId).orElseThrow(()-> new RuntimeException("Player not found "));
        Team team=teamRepository.findById(teamId).orElseThrow(()-> new RuntimeException("Team not found"));
        player.setTeam(team);
        player.setStatus(PlayerStatus.ACTIVE);
        return playerRepository.save(player);
    }
}
