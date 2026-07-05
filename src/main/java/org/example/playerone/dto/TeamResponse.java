package org.example.playerone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.playerone.model.Team;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private long id;
    private String teamName;
    private String gameDivision;
    private Long coachId;
    private List<PlayerResponse> players;

    public static TeamResponse fromEntity(Team team) {
        if (team == null) {
            return null;
        }
        return TeamResponse.builder()
                .id(team.getId())
                .teamName(team.getTeamName())
                .gameDivision(team.getGameDivision())
                .coachId(team.getCoachId())
                .players(team.getPlayers() != null ? 
                        team.getPlayers().stream().map(PlayerResponse::fromEntity).collect(Collectors.toList()) : 
                        Collections.emptyList())
                .build();
    }
}
