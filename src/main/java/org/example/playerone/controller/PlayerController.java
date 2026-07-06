package org.example.playerone.controller;

import jakarta.validation.Valid;
import org.example.playerone.dto.ApiResponse;
import org.example.playerone.dto.PlayerCreationRequest;
import org.example.playerone.dto.PlayerResponse;
import org.example.playerone.model.Player;
import org.example.playerone.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ApiResponse<PlayerResponse> createPlayer(@Valid @RequestBody PlayerCreationRequest request) {
        Player player = new Player(
                request.getGamerTag(),
                request.getRealName(),
                request.getPrimaryRole(),
                request.getStatus()
        );
        Player savedPlayer = playerService.savePlayer(player);
        return ApiResponse.success(PlayerResponse.fromEntity(savedPlayer), "Player created successfully");
    }

    @GetMapping
    public ApiResponse<List<PlayerResponse>> getAllPlayers() {
        List<PlayerResponse> players = playerService.findAllPlayers().stream()
                .map(PlayerResponse::fromEntity)
                .collect(Collectors.toList());
        return ApiResponse.success(players, "All players retrieved successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<PlayerResponse> getPlayerById(@PathVariable Long id) {
        Player player = playerService.findPlayerById(id);
        return ApiResponse.success(PlayerResponse.fromEntity(player), "Player retrieved successfully");
    }

    @GetMapping("/tag/{gamerTag}")
    public ApiResponse<PlayerResponse> getPlayerByGamerTag(@PathVariable String gamerTag) {
        Player player = playerService.findPlayerByGamerTag(gamerTag);
        return ApiResponse.success(PlayerResponse.fromEntity(player), "Player retrieved by gamer tag successfully");
    }

    @GetMapping("/team/{teamId}")
    public ApiResponse<List<PlayerResponse>> getPlayersByTeam(@PathVariable Long teamId) {
        List<PlayerResponse> players = playerService.findPlayersByTeam(teamId).stream()
                .map(PlayerResponse::fromEntity)
                .collect(Collectors.toList());
        return ApiResponse.success(players, "Players for team ID " + teamId + " retrieved successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ApiResponse.success(null, "Player deleted successfully");
    }
}
