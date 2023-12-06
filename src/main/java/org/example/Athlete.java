package org.example;

public class Athlete {
    private long id;
    private String firstName;
    private String surname;
    private int dateBirth;
    private City city;

    public Athlete() {
    }

    public Athlete(long id, String firstName, String surname, int dateBirth, City city) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                ", surName='" + surname + '\'' +
                ", dateBirth=" + dateBirth +
                ", city=" + city +
                '}';
    }
}
