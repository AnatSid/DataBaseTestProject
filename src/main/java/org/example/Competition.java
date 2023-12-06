package org.example;

import java.util.Date;
import java.util.List;

public class Competition {
    private long id;
    private String name;
    private Date date;
    private City city;
    private List<RaceResult> raceResults;

    public Competition(long id, String name, Date date, City city) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.city = city;
    }

    public void setRaceResults(List<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", city=" + city +
                '}';
    }


}
