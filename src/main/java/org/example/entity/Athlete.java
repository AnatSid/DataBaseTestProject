package org.example.entity;

import java.time.LocalDate;

public class Athlete {
    private long id;
    private String firstName;
    private String surName;
    private LocalDate dateBirth;
    private City city;

    public Athlete() {
    }

    public Athlete(long id, String firstName, String surName, LocalDate dateBirth, City city) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.dateBirth = dateBirth;
        this.city = city;
    }

    public Athlete(String firstName, String surName, LocalDate dateBirth, City city) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateBirth = dateBirth;
        this.city = city;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", dateBirth=" + dateBirth +
                ", city=" + city +
                '}';
    }
}
