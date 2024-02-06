package org.example.service;

import org.example.entity.Athlete;
import org.example.dao.AthleteDao;
import org.example.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AthleteService {
    private final AthleteDao athleteDao;

    public AthleteService(AthleteDao athleteDao) {
        this.athleteDao = athleteDao;
    }

    public Athlete addAthlete(Athlete athlete) {
        if (athlete == null) {
            throw new IllegalArgumentException("Athlete cannot be null");
        }
        return athleteDao.create(athlete);
    }

    public void deleteAthlete(Athlete athlete) {
        if (athlete.getId() <= 0) {
            throw new IllegalArgumentException("Invalid athlete ID");
        }
        athleteDao.delete(athlete);
    }

    public Athlete getAthleteById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id should be positive values");
        }

        Optional<Athlete> optionalAthlete = athleteDao.findById(id);

        if (optionalAthlete.isPresent()) {
            return optionalAthlete.get();
        } else {
            throw new EntityNotFoundException("Athlete not found with ID: " + id);
        }
    }

    public List<Athlete> getAllAthletes() {
        return athleteDao.getAll();
    }

    public List<Athlete> getAthleteByCity(String cityName) {
        if (cityName == null) {
            throw new IllegalArgumentException("City name cannot be null");
        }
        return athleteDao.getAthletesByCity(cityName);
    }

    public List<Athlete> getAthletesWithBirthDateBetween(LocalDate minDateBirth, LocalDate maxDateBirth) {
        if (minDateBirth == null || maxDateBirth == null) {
            throw new IllegalArgumentException("Birth dates cannot be null");
        }
        if (minDateBirth.isAfter(maxDateBirth)) {
            throw new IllegalArgumentException("minDateBirth should be before or equal to maxDateBirth");
        }
        return athleteDao.getAthletesByBirthDate(minDateBirth, maxDateBirth);
    }

}