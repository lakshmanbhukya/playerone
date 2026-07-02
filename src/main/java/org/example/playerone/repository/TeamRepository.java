package org.example.playerone.repository;

import org.example.playerone.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository  extends JpaRepository<Team,Long> {
}
