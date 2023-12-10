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
