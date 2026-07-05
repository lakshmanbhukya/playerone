package org.example.playerone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.playerone.enums.PlayerRole;
import org.example.playerone.enums.PlayerStatus;

@Data
public class PlayerCreationRequest {
    @NotBlank(message = "Gamer tag is required")
    private String gamerTag;

    @NotBlank(message = "Real name is required")
    private String realName;

    @NotNull(message = "Primary role is required")
    private PlayerRole primaryRole;

    @NotNull(message = "Status is required")
    private PlayerStatus status;
}
