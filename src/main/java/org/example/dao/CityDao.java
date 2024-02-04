package org.example.dao;

import org.example.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/swim_base_2";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "1313";

    public City create(City city) {
        String insertQuery = "INSERT INTO City (name) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, city.getName());
            statement.executeUpdate();

            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    long id = generatedKey.getLong(1);
                    city.setId(id);
                } else {
                    throw new SQLException("Creating city failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding City to database", e);
        }

        return city;
    }

    public void delete(City city) {
        String deleteQuery = "DELETE FROM City WHERE City.id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setLong(1, city.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting City to database");
        }
    }

    public City getById(long id) {
        City city = null;
        String getByIdQuery = "SELECT * FROM City WHERE City.id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(getByIdQuery)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long idCity = resultSet.getLong("id");
                    String nameCity = resultSet.getString("name");
                    city = new City(idCity, nameCity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving city by ID from the database", e);
        }

        return city;
    }

    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        String getAllQuery = "SELECT * FROM City";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long cityId = resultSet.getLong("id");
                String cityName = resultSet.getString("name");
                cities.add(new City(cityId, cityName));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all cities from the database", e);
        }

        return cities;
    }

}
