package org.example;


import org.example.athleteService.AthleteController;
import org.example.athleteService.AthleteDao;

public class testMain {
    public static void main(String[] args) {
        String jsonCompetition = """
                {
                  "name": "SuperSwimDataBaseTest",
                  "date": "2020-01-22",
                  "city": {
                    "id": 1,
                    "name": "Gomel"
                  }
                }""";

        String jsonAthlete = """
                {
                  "firstName": "testName",
                  "surName": "testSurname",
                  "dateBirth": "1995",
                  "city": {
                    "id": 1,
                    "name": "Gomel"
                  }
                }""";


//        CompetitionDao competitionDao = new CompetitionDao();
//
//        List<Competition> competitions = competitionDao.getCompetitionsByCity("Gomel");
//        List<Competition> competitions1 = competitionDao.getAllCompetitionsInDataBase();
//        System.out.println(competitions);
//        System.out.println(competitions1);

        AthleteController athleteController = new AthleteController();

        Athlete athlete = athleteController.createAthleteFromJson(jsonAthlete);

        AthleteDao athleteDao = new AthleteDao();
        athleteDao.createAthletesInDataBase(athlete);



    }
}
