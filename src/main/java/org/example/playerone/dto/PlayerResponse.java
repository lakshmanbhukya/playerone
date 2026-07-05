package org.example.playerone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.playerone.enums.PlayerRole;
import org.example.playerone.enums.PlayerStatus;
import org.example.playerone.model.Player;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
    private long id;
    private String gamerTag;
    private String realName;
    private PlayerRole primaryRole;
    private PlayerStatus status;
    private Long teamId;
    private String teamName;

    public static PlayerResponse fromEntity(Player player) {
        if (player == null) {
            return null;
        }
        return PlayerResponse.builder()
                .id(player.getId())
                .gamerTag(player.getGamerTag())
                .realName(player.getRealName())
                .primaryRole(player.getPrimayRole())
                .status(player.getStatus())
                .teamId(player.getTeam() != null ? player.getTeam().getId() : null)
                .teamName(player.getTeam() != null ? player.getTeam().getTeamName() : null)
                .build();
    }
}
