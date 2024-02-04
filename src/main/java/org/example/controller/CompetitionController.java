package org.example.controller;


import org.example.entity.Competition;
import org.example.mapper.JsonMapper;
import org.example.service.CompetitionService;

import java.util.List;


public class CompetitionController {
    private final CompetitionService competitionService;
    private final JsonMapper jsonMapper;

    public CompetitionController(CompetitionService competitionService, JsonMapper jsonMapper) {
        this.competitionService = competitionService;
        this.jsonMapper = jsonMapper;
    }

    public String addCompetitionFromJson(String jsonRequest) {
        try {
            Competition competition = jsonMapper.mapCompetitionFromJson(jsonRequest);
            competitionService.addCompetition(competition);
            return jsonMapper.mapCompetitionToJson(competition);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error processing JSON request or adding competition: " + e.getMessage());
        }
    }

    public void deleteCompetition(long id) {
        Competition competitionToDelete = competitionService.getCompetitionById(id);
        competitionService.deleteCompetition(competitionToDelete);
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

}
