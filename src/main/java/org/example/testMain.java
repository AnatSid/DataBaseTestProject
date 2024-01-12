package org.example;


import org.example.competitionService123.CompetitionDao;

import java.util.List;

public class testMain {
    public static void main(String[] args) {
        String json = """
                {
                  "name": "SuperSwimDataBaseTest",
                  "date": "2020-01-22",
                  "city": {
                    "id": 1,
                    "name": "Gomel"
                  }
                }""";


        CompetitionDao competitionDao = new CompetitionDao();

        List<Competition> competitions = competitionDao.getCompetitionsByCity("Gomel");
        List<Competition> competitions1 = competitionDao.getAllCompetitionsInDataBase();
        System.out.println(competitions);
        System.out.println(competitions1);

    }
}
