package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.entity.Athlete;
import org.example.entity.City;
import org.example.entity.Competition;
import org.example.entity.Race;

public class JsonMapper {
    private final ObjectMapper objectMapper;

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Competition mapCompetitionFromJson(String json) {
        try {
            return objectMapper.readValue(json, Competition.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing JSON for Competition", e);
        }
    }

    public String mapCompetitionToJson(Competition competition) {
        try {
            return objectMapper.writeValueAsString(competition);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error mapping Competition to JSON", e);
        }
    }

    public Athlete mapAthleteFromJson(String json) {
        try {
            return objectMapper.readValue(json, Athlete.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing JSON for Athlete", e);
        }
    }

    public String mapAthleteToJson(Athlete athlete) {
        try {
            return objectMapper.writeValueAsString(athlete);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error mapping Athlete to JSON", e);
        }
    }


    public Race mapRaceFromJson(String json) {
        try {
            return objectMapper.readValue(json, Race.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing JSON for Race", e);
        }
    }

    public String mapRaceToJson(Race race) {
        try {
            return objectMapper.writeValueAsString(race);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error mapping Race to JSON", e);
        }
    }

    public City mapCityFromJson(String json) {
        try {
            return objectMapper.readValue(json, City.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing JSON for City", e);
        }
    }

    public String mapCityToJson(City city) {
        try {
            return objectMapper.writeValueAsString(city);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error mapping City to JSON", e);
        }
    }

}
