package org.example.controller;


import org.example.entity.Competition;
import org.example.mapper.CompetitionJsonMapper;
import org.example.service.CompetitionService;

import java.util.List;


public class CompetitionController {
    private final CompetitionService competitionService;
    private final CompetitionJsonMapper competitionJsonMapper;

    public CompetitionController(CompetitionService competitionService, CompetitionJsonMapper competitionJsonMapper) {
        this.competitionService = competitionService;
        this.competitionJsonMapper = competitionJsonMapper;
    }

    public void addCompetitionFromJson(String jsonRequest) {
        try {
            Competition competition = competitionJsonMapper.mapCompetitionFromJson(jsonRequest);
            competitionService.addCompetition(competition);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException( "Error processing JSON request: ", e);
        }
    }

    public Competition getCompetitionById(long id) {
        return competitionService.getCompetitionById(id);
    }

    public List<Competition> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    public List<Competition> getCompetitionsByCity(String cityName) {
        return competitionService.getCompetitionsByCity(cityName);
    }

    public void deleteCompetition(long id) {
        Competition competitionToDelete = competitionService.getCompetitionById(id);
        competitionService.deleteCompetition(competitionToDelete);
    }


}
