package org.example.controller;

import org.example.entity.Athlete;
import org.example.mapper.AthleteJsonMapper;
import org.example.service.AthleteService;


public class AthleteController {
    private final AthleteService athleteService;
    private final AthleteJsonMapper athleteJsonMapper;

    public AthleteController(AthleteService athleteService, AthleteJsonMapper athleteJsonMapper) {
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