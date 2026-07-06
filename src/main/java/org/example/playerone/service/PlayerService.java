package org.example.playerone.service;

import org.example.playerone.exception.ResourceNotFoundException;
import org.example.playerone.model.Player;
import org.example.playerone.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID: " + id));
    }

    public Player findPlayerByGamerTag(String gamerTag) {
        return playerRepository.findPlayerByGamerTag(gamerTag)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with gamer tag: " + gamerTag));
    }

    public List<Player> findPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public void deletePlayer(Long id) {
        Player player = findPlayerById(id);
        playerRepository.delete(player);
    }
}
