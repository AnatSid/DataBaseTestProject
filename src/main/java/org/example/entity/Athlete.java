package org.example.entity;

public class Athlete {
    private long id;
    private String firstName;
    private String surName;
    private int dateBirth;
    private City city;

    public Athlete() {
    }

    public Athlete(long id, String firstName, String surName, int dateBirth, City city) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.dateBirth = dateBirth;
        this.city = city;
    }

    public Athlete(String firstName, String surName, int dateBirth, City city) {
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

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
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
