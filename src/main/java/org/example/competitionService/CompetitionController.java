package org.example.competitionService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Competition;

public class CompetitionController {
    private final ObjectMapper objectMapper;

    public CompetitionController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Competition createCompetitionFromJson(String jsonRequest) {
        try {
            return objectMapper.readValue(jsonRequest, Competition.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON for Competition", e);
        }
    }
}
