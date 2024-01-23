package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.entity.Competition;

public class CompetitionJsonMapper {
    private final ObjectMapper objectMapper;

    public CompetitionJsonMapper(ObjectMapper objectMapper) {
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

}
