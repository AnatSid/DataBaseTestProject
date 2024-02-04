package org.example.controller;

import org.example.entity.Athlete;
import org.example.mapper.JsonMapper;
import org.example.service.AthleteService;

import java.time.LocalDate;
import java.util.List;


public class AthleteController {
    private final AthleteService athleteService;
    private final JsonMapper athleteJsonMapper;

    public AthleteController(AthleteService athleteService, JsonMapper athleteJsonMapper) {
        this.athleteService = athleteService;
        this.athleteJsonMapper = athleteJsonMapper;
    }

    public String addAthleteFromJson(String jsonRequest) {
        try {
            Athlete athlete = athleteJsonMapper.mapAthleteFromJson(jsonRequest);
            athleteService.addAthlete(athlete);
            return athleteJsonMapper.mapAthleteToJson(athlete);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error processing JSON request: ", e);
        }
    }

    public void deleteAthlete(long id) {
        Athlete athleteToDelete = athleteService.getAthleteById(id);
        athleteService.deleteAthlete(athleteToDelete);
    }

    public void getAthleteById(long id) {
        athleteService.getAthleteById(id);
    }

    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    public List<Athlete> getAllAthletesByCity(String cityName) {
        return athleteService.getAthleteByCity(cityName);
    }

    public List<Athlete> getAthletesWithBirthDateBetween(LocalDate minDateBirth, LocalDate maxDateBirth) {
        return athleteService.getAthletesWithBirthDateBetween(minDateBirth, maxDateBirth);
    }


}
