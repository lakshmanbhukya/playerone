package org.example.playerone.controller;

import org.example.playerone.dto.ApiResponse;
import org.example.playerone.dto.PlayerResponse;
import org.example.playerone.model.Player;
import org.example.playerone.service.RosterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rosters")
public class RosterController {

    private final RosterService rosterService;

    public RosterController(RosterService rosterService) {
        this.rosterService = rosterService;
    }

    @PutMapping("/teams/{teamId}/players/{playerId}")
    public ApiResponse<PlayerResponse> assignPlayerToTeam(@PathVariable Long playerId, @PathVariable Long teamId) {
        Player updatedPlayer = rosterService.AssignPlayerToTeam(playerId, teamId);
        return ApiResponse.success(PlayerResponse.fromEntity(updatedPlayer), "Player assigned to team successfully");
    }

    @PutMapping("/teams/unassign/{playerId}")
    public ApiResponse<PlayerResponse> unassignPlayerFromTeam(@PathVariable Long playerId) {
        Player updatedPlayer = rosterService.UnassignPlayerFromTeam(playerId);
        return ApiResponse.success(PlayerResponse.fromEntity(updatedPlayer), "Player unassigned from team successfully");
    }
}
