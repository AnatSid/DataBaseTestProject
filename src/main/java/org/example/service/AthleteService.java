package org.example.service;
import org.example.entity.Athlete;
import org.example.dao.AthleteDao;

public class AthleteService {
    private final AthleteDao athleteDao;

    public AthleteService(AthleteDao athleteDao) {
        this.athleteDao = athleteDao;
    }

    public void addAthlete(Athlete athlete) {
        if (athlete == null) {
            throw new IllegalArgumentException("Athlete cannot be null");
        }
        athleteDao.create(athlete);
    }

}