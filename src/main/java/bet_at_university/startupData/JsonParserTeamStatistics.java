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
        url = new URL("http://api.football-data.org/v1/competitions/445/leagueTable");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("standing");

        for (int i = 0; i < 20; i++) {
            JSONObject jsonTeamStatistics = jsonArray.getJSONObject(i);
            teamStatistics.setId(i + 1);
            teamStatistics.setWonMatches(jsonTeamStatistics.getInt("wins"));
            teamStatistics.setLostMatches(jsonTeamStatistics.getInt("losses"));
            teamStatistics.setDrawMatches(jsonTeamStatistics.getInt("draws"));
            teamStatistics.setGoals(jsonTeamStatistics.getInt("goals"));
            teamStatistics.setPlayedGames(jsonTeamStatistics.getInt("playedGames"));
            teamStatistics.setPoints(jsonTeamStatistics.getInt("points"));
            teamStatistics.setImg(jsonTeamStatistics.getString("crestURI"));
            teamStatistics.setLeagueName("Premier League");
            teamStatisticsRepository.save(teamStatistics);

        }
    }
}
