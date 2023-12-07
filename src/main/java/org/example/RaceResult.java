package org.example;

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

}
