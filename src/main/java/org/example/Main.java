package org.example;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/swim_base_2";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "1313";

    public static void main(String[] args) {
        Competition competition = null;
        List<RaceResult> raceResults = new ArrayList<>();
        String sqlCompetition = """
                SELECT
                comp.id,
                comp.name,
                comp.date,
                comp.id_city,
                c.name AS city_name
                FROM
                Competitions comp
                JOIN
                City c ON comp.id_city = c.id
                WHERE comp.id = 1
                """;

        String sqlRaceResults = """
                    SELECT
                    r.id AS id_race, r.style,r.distance,
                    ath.id AS id_athlete ,ath.first_name,ath.surname,ath.date_birth,ath.id_city,
                    c.name AS city_name,
                    rr.result_time,
                    rr.id_competition,
                    rr.points

                    FROM race_result rr

                    JOIN Race r ON rr.id_race = r.id
                    JOIN Athletes ath ON rr.id_athlete = ath.id
                    JOIN City c ON ath.id_city = c.id
                    WHERE rr.id_competition = 1""";

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             Statement statementCompetition = connection.createStatement();
             Statement statementRaceResults = connection.createStatement();
             ResultSet resultSet = statementCompetition.executeQuery(sqlCompetition);
             ResultSet resultSet2 = statementRaceResults.executeQuery(sqlRaceResults)) {

            while (resultSet.next()) {
                long compId = resultSet.getInt("id");
                String compName = resultSet.getString("name");
                Date compDate = resultSet.getDate("date");
                long idCity = resultSet.getInt("id_city");
                String cityName = resultSet.getString("city_name");
                competition = new Competition(compId, compName, compDate, new City(idCity, cityName));
            }

            assert competition != null;

            while (resultSet2.next()) {
                long idRace = resultSet2.getInt("id_race");
                String style = resultSet2.getString("style");
                SwimStyle swimStyle = SwimStyle.valueOf(style.toUpperCase());
                int distance = resultSet2.getInt("distance");
                Race race = new Race(idRace, swimStyle, distance);

                long idAthlete = resultSet2.getInt("id_athlete");
                String firstName = resultSet2.getString("first_name");
                String surname = resultSet2.getString("surname");
                int date = resultSet2.getInt("date_birth");
                City city = new City(resultSet2.getInt("id_city"), resultSet2.getString("city_name"));
                Athlete athlete = new Athlete(idAthlete, firstName, surname, date, city);

                LocalTime localTime = resultSet2.getTime("result_time").toLocalTime();
                int points = resultSet2.getInt("points");

                RaceResult raceResult = new RaceResult(race, athlete, localTime, competition, points);
                raceResults.add(raceResult);
            }

            competition.setRaceResults(raceResults);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(competition);
        System.out.println(raceResults);

    }
}
