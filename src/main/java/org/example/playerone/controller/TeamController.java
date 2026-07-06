package org.example.playerone.controller;

import jakarta.validation.Valid;
import org.example.playerone.dto.ApiResponse;
import org.example.playerone.dto.TeamCreationRequest;
import org.example.playerone.dto.TeamResponse;
import org.example.playerone.model.Team;
import org.example.playerone.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ApiResponse<TeamResponse> createTeam(@Valid @RequestBody TeamCreationRequest request) {
        Team team = new Team(
                request.getTeamName(),
                request.getGameDivison(),
                request.getCoachId()
        );
        Team savedTeam = teamService.saveTeam(team);
        return ApiResponse.success(TeamResponse.fromEntity(savedTeam), "Team created successfully");
    }

    @GetMapping
    public ApiResponse<List<TeamResponse>> getAllTeams() {
        List<TeamResponse> teams = teamService.findAllTeams().stream()
                .map(TeamResponse::fromEntity)
                .collect(Collectors.toList());
        return ApiResponse.success(teams, "All teams retrieved successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<TeamResponse> getTeamById(@PathVariable Long id) {
        Team team = teamService.findTeamById(id);
        return ApiResponse.success(TeamResponse.fromEntity(team), "Team retrieved successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ApiResponse.success(null, "Team deleted successfully");
    }
}
