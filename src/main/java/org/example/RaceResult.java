package org.example;

import java.sql.Time;
import java.time.LocalTime;

public class RaceResult {

    private Race race;
    private Athlete athlete;
    private LocalTime resultTime;
    private Competition competition;
    private int points;


    public RaceResult(Race race, Athlete athlete, LocalTime resultTime, Competition competition, int points) {
        this.race = race;
        this.athlete = athlete;
        this.resultTime = resultTime;
        this.competition = competition;
        this.points = points;
    }


    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }


    public void setResultTime(LocalTime resultTime) {
        this.resultTime = Time.valueOf(resultTime).toLocalTime();
    }

    @Override
    public String toString() {
        return "RaceResult{" +
                "race=" + race +
                ", athlete=" + athlete +
                ", resultTime=" + resultTime +
                ", competition=" + competition +
                ", points=" + points +
                '}';
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
