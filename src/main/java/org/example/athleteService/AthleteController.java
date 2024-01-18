package org.example.athleteService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Athlete;


public class AthleteController {
    private final ObjectMapper objectMapper;

    public AthleteController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Athlete createAthleteFromJson(String jsonRequest) {
        try {
            return objectMapper.readValue(jsonRequest, Athlete.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON for Athlete", e);
        }
    }

}
