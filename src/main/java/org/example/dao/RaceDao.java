package org.example.dao;

import org.example.entity.Race;
import org.example.entity.SwimStyle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaceDao {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/swim_base_2";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "1313";

    public Race create(Race race) {
        String insertQuery = "INSERT INTO RACE(style,distance) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, race.getStyle().toString());
            statement.setInt(2, race.getDistance());
            statement.executeUpdate();

            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    long id = generatedKey.getLong("id");
                    race.setId(id);
                } else {
                    throw new SQLException("Creating race failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding Race to database", e);
        }

        return race;
    }

    public void delete(Race race) {
        String deleteQuery = "DELETE FROM race WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, race.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Race from database", e);
        }
    }

    public Race getById(long id) {
        String selectByIdQuery = "SELECT race.* FROM race WHERE id = ?";
        Race race = null;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long idRace = resultSet.getLong("id");
                    SwimStyle style = SwimStyle.valueOf(resultSet.getString("style"));
                    int distance = resultSet.getInt("distance");
                    race = new Race(idRace, style, distance);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving race by ID from database", e);
        }
        return race;
    }

    public List<Race> getAll() {
        String getAllQuery = "SELECT * FROM Race";
        List<Race> races = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                SwimStyle style = SwimStyle.valueOf(resultSet.getString("style"));
                int distance = resultSet.getInt("distance");
                races.add(new Race(id, style, distance));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all race from database", e);
        }
        return races;
    }

    public List<Race> getAllRaceBySwimStyle(SwimStyle style) {
        String getByStyleQuery = "SELECT * FROM race WHERE race.style = ?";
        List<Race> races = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(getByStyleQuery)) {

            statement.setString(1, style.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    SwimStyle swimStyle = SwimStyle.valueOf(resultSet.getString("style"));
                    int distance = resultSet.getInt("distance");
                    races.add(new Race(id, swimStyle, distance));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving race by swim style from database", e);
        }

        return races;
    }
}
