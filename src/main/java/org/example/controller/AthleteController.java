package org.example.controller;

import org.example.entity.Athlete;
import org.example.mapper.JsonMapper;
import org.example.service.AthleteService;


public class AthleteController {
    private final AthleteService athleteService;
    private final JsonMapper athleteJsonMapper;

    public AthleteController(AthleteService athleteService, JsonMapper athleteJsonMapper) {
        this.athleteService = athleteService;
        this.athleteJsonMapper = athleteJsonMapper;
    }

    public void addAthleteFromJson(String jsonRequest) {
        try {
            Athlete athlete = athleteJsonMapper.mapAthleteFromJson(jsonRequest);
            athleteService.addAthlete(athlete);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException( "Error processing JSON request: ", e);
        }
    }

}
