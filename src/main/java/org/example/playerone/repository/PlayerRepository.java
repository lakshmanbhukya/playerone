package org.example.playerone.repository;

import org.example.playerone.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeamId(Long teamId);
    Optional<Player> findPlayerByGamerTag(String gamerTag);

}
