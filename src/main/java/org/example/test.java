package org.example;

public class test {
    public static void main(String[] args) {
        String Json = """
                {
                  "name": "SuperSwimCupForTestDataBase",
                  "date": "2020-01-22",
                  "city": {
                    "id": 1,
                    "name": "Gomel"
                  }
                }""";

        CompetitionService competitionService = new CompetitionService();
        Competition competition = competitionService.createCompetitionFromJson(Json);
        competitionService.addCompetitionToDataBase(competition);
        System.out.println(competition.getId());



    }
}
