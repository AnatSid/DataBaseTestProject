package org.example.service;

import org.example.entity.Competition;
import org.example.dao.CompetitionDao;

import java.util.List;


public class CompetitionService {
    private final CompetitionDao competitionDao;


    public CompetitionService(CompetitionDao competitionDao) {
        this.competitionDao = competitionDao;
    }

    public Competition addCompetition(Competition competition) {
        if (competition == null) {
            throw new IllegalArgumentException("Competition cannot be null");
        }
        return competitionDao.create(competition);
    }

    public void deleteCompetition(Competition competition) {
        if (competition.getId() <= 0) {
            throw new IllegalArgumentException("Invalid competition ID");
        }
        competitionDao.delete(competition);
    }

    public Competition getCompetitionById(long competitionId) {
        if (competitionId <= 0) {
            throw new IllegalArgumentException("Invalid competition ID");
        }
        return competitionDao.getById(competitionId);
    }

    public List<Competition> getAllCompetitions() {
        return competitionDao.getAll();
    }

    public List<Competition> getCompetitionsByCity(String cityName) {
        if (cityName == null) {
            throw new IllegalArgumentException("City name cannot be null");
        }
        return competitionDao.getCompetitionsByCity(cityName);
    }


}