package org.example.playerone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="team_name", nullable = false, unique = true)
    private String teamName;

    @Column(name="game_division", nullable = false)
    private String gameDivision;

    @Column(name ="coach_id")
    private Long coachId;

    @OneToMany(mappedBy="team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();


}
