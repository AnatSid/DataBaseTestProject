package org.example.competitionService;

import org.example.Competition;

import java.util.List;


public class CompetitionService {
    private final CompetitionDao competitionDao = new CompetitionDao();

    public void addCompetition(Competition competition) {
        if(competition == null){
            throw new RuntimeException("Competition cannot be null");
        }
        competitionDao.createCompetitionInDatabase(competition);
    }

    public void deleteCompetition(Competition competition) {
        if (competition.getId() == 0) {
            throw new IllegalArgumentException("Competition ID cannot be 0");
        }
        competitionDao.deleteCompetitionInDatabase(competition);
    }

    public Competition getCompetitionById(long competitionId) {
        if (competitionId == 0) {
            throw new IllegalArgumentException("Competition ID cannot be 0");
        }
        return competitionDao.getCompetitionById(competitionId);
    }

    public List<Competition> getAllCompetitions() {
        return competitionDao.getAllCompetitionsInDataBase();
    }

    public List<Competition> getCompetitionsByCity(String cityName) {
        if (cityName == null) {
            throw new IllegalArgumentException("City name cannot be null");
        }
        return competitionDao.getCompetitionsByCity(cityName);
    }


}