package org.example;

import java.time.LocalDate;
import java.util.List;

public class Competition {
    private long id;
    private String name;
    private LocalDate date;
    private City city;
    private List<RaceResult> raceResults;

    public Competition(long id, String name, LocalDate date, City city) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.city = city;
    }

    public Competition(String name, LocalDate date, City city) {
        this.name = name;
        this.date = date;
        this.city = city;
    }

    public Competition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(List<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", city=" + city +
                ", raceResults=" + raceResults +
                '}';
    }


}
