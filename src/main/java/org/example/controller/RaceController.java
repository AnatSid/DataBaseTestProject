package org.example.controller;

import org.example.entity.Race;
import org.example.entity.SwimStyle;
import org.example.mapper.JsonMapper;
import org.example.service.RaceService;

import java.util.List;

public class RaceController {
    private final RaceService raceService;
    private final JsonMapper jsonMapper;

    public RaceController(RaceService raceService, JsonMapper jsonMapper) {
        this.raceService = raceService;
        this.jsonMapper = jsonMapper;
    }

    public String addRaceFromJson(String jsonRace) {
        try {
            Race race = jsonMapper.mapRaceFromJson(jsonRace);
            raceService.addRace(race);
            return jsonMapper.mapRaceToJson(race);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error processing JSON request or adding race: " + e.getMessage());
        }
    }

    public void deleteRace(long id) {
        Race raceToDelete = raceService.getRaceById(id);
        raceService.deleteRace(raceToDelete);
    }

    public void getRaceById(long id) {
        raceService.getRaceById(id);
    }

    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    public List<Race> getAllRacesBySwimStyle(SwimStyle swimStyle) {
        return raceService.getRacesByStyle(swimStyle);
    }
}
