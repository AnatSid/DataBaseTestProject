package org.example.competitionService;

import org.example.City;
import org.example.Competition;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitionDao {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/swim_base_2";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "1313";


    public void createCompetitionInDatabase(Competition competition) {
        String insertQuery = "INSERT INTO Competitions (name, date, id_city) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setString(1, competition.getName());
            statement.setDate(2, Date.valueOf(competition.getDate()));
            statement.setLong(3, competition.getCity().getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding competition to database", e);
        }
    }

    public void deleteCompetitionInDatabase(Competition competition) {
        String deleteQuery = "DELETE FROM Competitions WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, competition.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting competition from database", e);
        }
    }

    public Competition getCompetitionById(long competitionId) {
        String selectByIdQuery =
                "SELECT Competitions.id, Competitions.name, Competitions.date, Competitions.id_city," +
                        " c.name AS city_name " +
                        "FROM Competitions " +
                        "JOIN City c ON Competitions.id_city = c.id " +
                        "WHERE Competitions.id = ?";

        Competition competition = null;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {

            statement.setLong(1, competitionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    LocalDate localDate = resultSet.getDate("date").toLocalDate();
                    long idCity = resultSet.getLong("id_city");
                    String cityName = resultSet.getString("city_name");
                    City city = new City(idCity, cityName);
                    competition = new Competition(id, name, localDate, city);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving competition by ID from the database", e);
        }
        return competition;
    }

    public List<Competition> getAllCompetitionsInDataBase() {
        String selectAllQuery =
                "SELECT Competitions.*, City.name AS city_name " +
                        "FROM Competitions " +
                        "JOIN City ON Competitions.id_city = City.id";
        List<Competition> competitions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                LocalDate localDate = resultSet.getDate("date").toLocalDate();
                long idCity = resultSet.getLong("id_city");
                String cityName = resultSet.getString("city_name");
                City city = new City(idCity, cityName);
                competitions.add(new Competition(id, name, localDate, city));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all competitions from the database", e);
        }

        return competitions;
    }

    public List<Competition> getCompetitionsByCity(String cityName) {
        String selectByCityQuery =
                "SELECT Competitions.*, City.name AS city_name " +
                        "FROM Competitions " +
                        "JOIN City ON Competitions.id_city = City.id " +
                        "WHERE City.name = ?";
        List<Competition> competitions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByCityQuery)) {

            statement.setString(1, cityName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    LocalDate localDate = resultSet.getDate("date").toLocalDate();
                    long idCity = resultSet.getLong("id_city");
                    City city = new City(idCity, cityName);
                    competitions.add(new Competition(id, name, localDate, city));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving competitions by city from the database", e);
        }

        return competitions;
    }


}
