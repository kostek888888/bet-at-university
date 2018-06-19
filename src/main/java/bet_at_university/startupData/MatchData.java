package bet_at_university.startupData;

import bet_at_university.database.model.Match;
import bet_at_university.database.model.Team;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.database.repository.TeamRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MatchData {

    private ArrayList<Team> teamArrayList = new ArrayList<>();
    private URL url;
    private Scanner scanner;
    private String json;

    public void addMatch( MatchRepository matchRepository, TeamRepository teamRepository) throws IOException {
        Match match = new Match();
        teamArrayList = (ArrayList<Team>) teamRepository.findAll();
        url = new URL("http://api.football-data.org/v1/competitions/467/fixtures");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("fixtures");


        for(int i =0; i<48; i++){
            JSONObject jsonMatch = jsonArray.getJSONObject(i);
            JSONObject jsonMatchResult = jsonArray.getJSONObject(i).getJSONObject("result");

            match.setId(i+1);
            match.setMatchDateAndTime(jsonMatch.getString("date").substring(0, 10)+" "+jsonMatch.getString("date").substring(11, 16));

            if(jsonMatch.getString("homeTeamName").equals(null) || jsonMatch.getString("awayTeamName").equals(null)){
                match.setHomeTeamId(null);
                match.setAwayTeamId(null);
            }
            else {
                for(int j=0; j<32; j++){
                    if(teamArrayList.get(j).getName().equals(jsonMatch.getString("homeTeamName"))){
                        match.setHomeTeamId(teamArrayList.get(j));
                        break;
                    }
                }
                for(int k=0; k<32; k++){
                    if(teamArrayList.get(k).getName().equals(jsonMatch.getString("awayTeamName"))){
                        match.setAwayTeamId(teamArrayList.get(k));
                        break;
                    }
                }
            }
            if(jsonMatchResult.get("goalsHomeTeam").toString().equals("null") ||
                    jsonMatchResult.get("goalsAwayTeam").toString().equals("null")){

                match.setHomeTeamScore(-1);
                match.setAwayTeamScore(-1);
            }
            else {

                match.setHomeTeamScore(Integer.parseInt(jsonMatchResult.get("goalsHomeTeam").toString()));
                match.setAwayTeamScore(Integer.parseInt(jsonMatchResult.get("goalsAwayTeam").toString()));
            }
            matchRepository.save(match);
        }

    }
}
