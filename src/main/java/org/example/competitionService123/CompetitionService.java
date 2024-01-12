package org.example.competitionService123;

import org.example.Competition;


public class CompetitionService {
    private final CompetitionDao competitionDao = new CompetitionDao();

    public void addCompetitionToDataBase(Competition competition) {
        competitionDao.createCompetitionInDatabase(competition);
    }

    public void deleteCompetitionFromDataBase(Competition competition) {
        if (competition.getId() == 0) {
            throw new IllegalArgumentException("Competition ID cannot be 0");
        }
        competitionDao.deleteCompetitionInDatabase(competition);
    }

}