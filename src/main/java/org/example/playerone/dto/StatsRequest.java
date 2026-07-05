package org.example.playerone.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsRequest {
    @NotNull(message = "Player ID is required")
    private Long playerId;

    @NotNull(message = "Match date is required")
    private LocalDate matchDate;

    @NotBlank(message = "Win status is required")
    private String winStatus;

    @NotNull(message = "KDA ratio is required")
    @Min(value = 0, message = "KDA ratio cannot be negative")
    private Double kdaRatio;

    @NotNull(message = "Score is required")
    private Integer score;
}
