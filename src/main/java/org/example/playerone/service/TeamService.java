package org.example.playerone.service;

import org.example.playerone.exception.ResourceNotFoundException;
import org.example.playerone.model.Team;
import org.example.playerone.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team findTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + id));
    }

    public void deleteTeam(Long id) {
        Team team = findTeamById(id);
        teamRepository.delete(team);
    }
}
