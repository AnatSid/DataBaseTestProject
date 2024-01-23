package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.entity.Athlete;

public class AthleteJsonMapper {
    private final ObjectMapper objectMapper;

    public AthleteJsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    public Athlete mapAthleteFromJson(String json) {
        try {
            return objectMapper.readValue(json, Athlete.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing JSON for Athlete", e);
        }
    }

}
