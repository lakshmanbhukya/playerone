package org.example.playerone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamCreationRequest {
    @NotBlank(message = "Team name is required")
    private String teamName;

    @NotBlank(message = "Game division is required")
    private String gameDivison;

    @NotNull(message = "Coach ID is required")
    private Long coachId;
}
