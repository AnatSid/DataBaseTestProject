package org.example.service;

import org.example.dao.RaceDao;
import org.example.entity.Race;
import org.example.entity.SwimStyle;

import java.util.List;

public class RaceService {
    private final RaceDao raceDao;

    public RaceService(RaceDao raceDao) {
        this.raceDao = raceDao;
    }

    public Race addRace(Race race) {
        if (race == null) {
            throw new IllegalArgumentException("Race cannot be null");
        }
        return raceDao.create(race);
    }

    public void deleteRace(Race race) {
        if (race.getId() <= 0) {
            throw new IllegalArgumentException("Invalid race ID");
        }
        raceDao.delete(race);
    }

    public Race getRaceById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid race ID");
        }
        return raceDao.getById(id);
    }

    public List<Race> getAllRaces() {
        return raceDao.getAll();
    }

    public List<Race> getRacesByStyle(SwimStyle swimStyle) {
        if (swimStyle == null) {
            throw new IllegalArgumentException("SwimStyle cannot be null");
        }
        return raceDao.getAllRaceBySwimStyle(swimStyle);
    }

}
