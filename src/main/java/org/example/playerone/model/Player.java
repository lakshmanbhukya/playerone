package org.example.playerone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.playerone.enums.PlayerRole;
import org.example.playerone.enums.PlayerStatus;

@Entity
@Table(name="player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "gamer_tag", nullable = false, unique = true)
    private String gamerTag;

    @Column(name="real_name",nullable = false)
    private String realName;

    @Enumerated(EnumType.STRING)
    @Column(name = "primay_role", nullable = false)
    private PlayerRole primayRole;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private PlayerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}
