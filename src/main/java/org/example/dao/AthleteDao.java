package org.example.dao;

import org.example.entity.Athlete;
import org.example.entity.City;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AthleteDao {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/swim_base_2";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "1313";


    public Athlete create(Athlete athlete) {
        String insertQuery = "INSERT INTO Athletes (first_name,surname,date_birth,id_city) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, athlete.getFirstName());
            statement.setString(2, athlete.getSurName());
            statement.setDate(3, Date.valueOf(athlete.getDateBirth()));
            statement.setLong(4, athlete.getCity().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    athlete.setId(id);
                } else {
                    throw new SQLException("Creating athlete failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding athlete to database", e);
        }

        return athlete;
    }

    public void delete(Athlete athlete) {
        String deleteQuery = "DELETE FROM Athletes WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, athlete.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting athlete to database", e);
        }
    }

    public Athlete getById(long athleteId) {
        String selectByIdQuery = "SELECT Athletes.*, c.name AS city_name "
                + "FROM Athletes "
                + "JOIN City c ON Athletes.id_city = c.id "
                + "WHERE Athletes.id = ?";

        Athlete athlete = null;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {

            statement.setLong(1, athleteId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String surname = resultSet.getString("surname");
                    LocalDate dateBirth = resultSet.getDate("date_birth").toLocalDate();
                    long idCity = resultSet.getLong("id_city");
                    String cityName = resultSet.getString("city_name");
                    athlete = new Athlete(athleteId, firstName, surname, dateBirth, new City(idCity, cityName));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving athlete by ID from the database", e);
        }
        return athlete;
    }

    public List<Athlete> getAll() {
        String selectAllQuery = "SELECT Athletes.*, c.name AS city_name "
                + "FROM Athletes "
                + "JOIN City c ON Athletes.id_city = c.id";

        List<Athlete> athletes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String surname = resultSet.getString("surname");
                LocalDate dateBirth = resultSet.getDate("date_birth").toLocalDate();
                long idCity = resultSet.getLong("id_city");
                String cityName = resultSet.getString("city_name");
                Athlete athlete = new Athlete(id, firstName, surname, dateBirth, new City(idCity, cityName));
                athletes.add(athlete);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all athletes from the database", e);
        }
        return athletes;
    }

    public List<Athlete> getAthletesByCity(String cityName) {
        String selectByCityQuery = "SELECT Athletes.*, c.name AS city_name "
                + "FROM Athletes "
                + "JOIN City c ON Athletes.id_city = c.id "
                + "WHERE c.name = ?";

        List<Athlete> athletes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByCityQuery)) {

            statement.setString(1, cityName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String surname = resultSet.getString("surname");
                    LocalDate dateBirth = resultSet.getDate("date_birth").toLocalDate();
                    long idCity = resultSet.getLong("id_city");
                    Athlete athlete = new Athlete(id, firstName, surname, dateBirth, new City(idCity, cityName));
                    athletes.add(athlete);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving athletes by city from the database", e);
        }

        return athletes;
    }


    public List<Athlete> getAthletesByBirthDate(LocalDate minDateBirth, LocalDate maxDateBirth) {
        List<Athlete> athletes = new ArrayList<>();
        String selectByBirthDateQuery = "SELECT Athletes.*, c.name AS city_name "
                + "FROM Athletes "
                + "JOIN City c ON Athletes.id_city = c.id "
                + "WHERE date_birth BETWEEN ? AND ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectByBirthDateQuery)) {

            statement.setDate(1, Date.valueOf(minDateBirth));
            statement.setDate(2, Date.valueOf(maxDateBirth));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String surname = resultSet.getString("surname");
                    LocalDate dateBirth = resultSet.getDate("date_birth").toLocalDate();
                    long idCity = resultSet.getLong("id_city");
                    String cityName = resultSet.getString("city_name");
                    Athlete athlete = new Athlete(id, firstName, surname, dateBirth, new City(idCity, cityName));
                    athletes.add(athlete);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving athletes by birth date from the database", e);
        }
        return athletes;
    }


}
