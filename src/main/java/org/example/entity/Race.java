package org.example.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.*;

public class Race {
    private long id;
    private SwimStyle style;
    private int distance;


    public Race(long id, SwimStyle style,int distance) {
        this.id = id;
        this.style = style;
        this.distance = distance;

    }

    public Race() {
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", distance=" + distance +
                ", style='" + style + '\'' +
                '}';
    }

    public static class CompetitionService {

        private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/swim_base_2";
        private static final String DATABASE_USER = "postgres";
        private static final String DATABASE_PASSWORD = "1313";
        private final ObjectMapper objectMapper;

        public CompetitionService() {
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

        public void addCompetitionToDataBase(Competition competition) {
            String insertQuery = "INSERT INTO Competitions (name, date, id_city) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, competition.getName());
                preparedStatement.setDate(2, Date.valueOf(competition.getDate()));
                preparedStatement.setLong(3, competition.getCity().getId());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idCompetition = generatedKeys.getLong(1);
                        competition.setId(idCompetition);
                    } else {
                        throw new SQLException("Failed to get generated key, no ID obtained.");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error adding competition to database", e);
            }
        }


    }
}
