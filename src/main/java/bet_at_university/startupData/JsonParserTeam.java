package bet_at_university.startupData;

import bet_at_university.database.model.Team;
import bet_at_university.database.model.TeamStatistics;
import bet_at_university.database.repository.TeamRepository;
import bet_at_university.database.repository.TeamStatisticsRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonParserTeam {

    private URL url;
    private Scanner scanner;
    private String json;
    private ArrayList<TeamStatistics> teamStatisticsArrayList = new ArrayList<>();


    public void addTeam(TeamRepository teamRepository, TeamStatisticsRepository teamStatisticsRepository) throws IOException {
        Team team = new Team();
        url = new URL("http://api.football-data.org/v1/competitions/445/leagueTable");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();
        teamStatisticsArrayList = (ArrayList<TeamStatistics>) teamStatisticsRepository.findAll();


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("standing");


        for(int i=0; i<20; i++){
            JSONObject jsonTeam = jsonArray.getJSONObject(i);
            team.setId(i+1);
            team.setName(jsonTeam.getString("teamName"));
            team.setTeamStatistics(teamStatisticsArrayList.get(i));
            teamRepository.save(team);
        }
    }
}
