package org.example.playerone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name="win_status", nullable = false)
    private String winStatus;

    @Column(name="kda_ratio" , nullable = false)
    private double kdaRatio;

    @Column(name = "score",nullable = false)
    private Integer score;

}
