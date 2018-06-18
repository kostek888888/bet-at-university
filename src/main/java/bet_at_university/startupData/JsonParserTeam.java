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
        url = new URL("http://api.football-data.org/v1/competitions/467/leagueTable");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();
        teamStatisticsArrayList = (ArrayList<TeamStatistics>) teamStatisticsRepository.findAll();


        String[] groupKey = {"A", "B", "C", "D", "E", "F", "G", "H"};
        JSONObject jsonObject = new JSONObject(json).getJSONObject("standings");

        int id =1;

        int key =0;
        for(int i=0; i<8; i++) {
            JSONArray jsonArray = jsonObject.getJSONArray(groupKey[key]);
            for (int j = 0; j < 4; j++) {
                JSONObject jsonTeam = jsonArray.getJSONObject(j);
                team.setId(id);
                team.setName(jsonTeam.getString("team"));
                team.setTeamStatistics(teamStatisticsArrayList.get(id-1));
                id++;
                teamRepository.save(team);
            }
            key++;
        }
    }
}
