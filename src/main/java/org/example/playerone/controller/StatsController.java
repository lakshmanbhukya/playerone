package org.example.playerone.controller;

import jakarta.validation.Valid;
import org.example.playerone.dto.ApiResponse;
import org.example.playerone.dto.StatsRequest;
import org.example.playerone.dto.StatsResponse;
import org.example.playerone.model.Stats;
import org.example.playerone.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping
    public ApiResponse<StatsResponse> recordStats(@Valid @RequestBody StatsRequest request) {
        Stats savedStats = statsService.saveStats(request);
        return ApiResponse.success(StatsResponse.fromEntity(savedStats), "Stats recorded successfully");
    }

    @GetMapping
    public ApiResponse<List<StatsResponse>> getAllStats() {
        List<StatsResponse> stats = statsService.findAllStats().stream()
                .map(StatsResponse::fromEntity)
                .collect(Collectors.toList());
        return ApiResponse.success(stats, "All stats retrieved successfully");
    }

    @GetMapping("/player/{playerId}")
    public ApiResponse<List<StatsResponse>> getStatsByPlayer(@PathVariable Long playerId) {
        List<StatsResponse> stats = statsService.findStatsByPlayerId(playerId).stream()
                .map(StatsResponse::fromEntity)
                .collect(Collectors.toList());
        return ApiResponse.success(stats, "Stats for player ID " + playerId + " retrieved successfully");
    }
}
