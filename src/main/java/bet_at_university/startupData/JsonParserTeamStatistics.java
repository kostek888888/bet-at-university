package bet_at_university.startupData;

import bet_at_university.database.model.TeamStatistics;
import bet_at_university.database.repository.TeamStatisticsRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class JsonParserTeamStatistics {
    private URL url;
    private Scanner scanner;
    private String json;

    public void addTeamStatistics(TeamStatisticsRepository teamStatisticsRepository) throws IOException {
        TeamStatistics teamStatistics = new TeamStatistics();
        url = new URL("http://api.football-data.org/v1/competitions/467/leagueTable");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();
        String[] groupKey = {"A", "B", "C", "D", "E", "F", "G", "H"};
        JSONObject jsonObject = new JSONObject(json).getJSONObject("standings");
        int id =1;

        int key =0;
        for(int i=0; i<8; i++){
            JSONArray jsonArray = jsonObject.getJSONArray(groupKey[key]);
            for(int j=0; j<4; j++){
                JSONObject jsonTeamStatistics = jsonArray.getJSONObject(j);
                teamStatistics.setId(id);
                teamStatistics.setImg(jsonTeamStatistics.getString("crestURI"));
                teamStatistics.setPoints(jsonTeamStatistics.getInt("points"));
                teamStatistics.setPlayedGames(jsonTeamStatistics.getInt("playedGames"));
                teamStatistics.setGoals(jsonTeamStatistics.getInt("goals"));
                teamStatistics.setGoalsAgainst(jsonTeamStatistics.getInt("goalsAgainst"));
                teamStatistics.setGoalDifference(jsonTeamStatistics.getInt("goalDifference"));
                teamStatistics.setLeagueName("World Cup 2018 Russia");
                id++;
                teamStatisticsRepository.save(teamStatistics);
            }
            key++;
        }
    }
}
