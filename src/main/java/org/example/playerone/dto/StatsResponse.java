package org.example.playerone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.playerone.model.Stats;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponse {
    private Long id;
    private Long playerId;
    private String gamerTag;
    private LocalDate matchDate;
    private String winStatus;
    private double kdaRatio;
    private Integer score;

    public static StatsResponse fromEntity(Stats stats) {
        if (stats == null) {
            return null;
        }
        return StatsResponse.builder()
                .id(stats.getId())
                .playerId(stats.getPlayer() != null ? stats.getPlayer().getId() : null)
                .gamerTag(stats.getPlayer() != null ? stats.getPlayer().getGamerTag() : null)
                .matchDate(stats.getMatchDate())
                .winStatus(stats.getWinStatus())
                .kdaRatio(stats.getKdaRatio())
                .score(stats.getScore())
                .build();
    }
}
