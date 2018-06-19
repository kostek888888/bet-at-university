package bet_at_university.controllers;

import bet_at_university.database.model.Match;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.startupData.MatchData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Controller
@RequestMapping(path = "/")
public class MatchController {
    private Match match = new Match();
    private Random random = new Random();
    private ArrayList<Match> matchArrayList = new ArrayList<>();
    private ArrayList<Match> matchToBetArrayList = new ArrayList<>();
    private URL url;
    private Scanner scanner;
    private String json;




    @Autowired
    private MatchRepository matchRepository;

    //@CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/matchToBet")
    public @ResponseBody ArrayList<Match> getAllMatchesToBet(){
        matchArrayList = (ArrayList<Match>) matchRepository.findAll();
        matchToBetArrayList.clear();
        for(int i =0; i < matchArrayList.size(); i++){
            if(matchArrayList.get(i).getAwayTeamScore() < 0){
                matchToBetArrayList.add(matchArrayList.get(i));
            }
        }
        System.out.print(matchToBetArrayList.size());
        return matchToBetArrayList;
    }

    //@CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/matchToNotBet")
    public @ResponseBody ArrayList<Match> getAllMatchesToNotBet(){
        matchArrayList = (ArrayList<Match>) matchRepository.findAll();
        matchToBetArrayList.clear();
        for(int i =0; i < matchArrayList.size(); i++){
            if(matchArrayList.get(i).getAwayTeamScore() >= 0){
                matchToBetArrayList.add(matchArrayList.get(i));
            }
        }
        System.out.print(matchToBetArrayList.size());
        return matchToBetArrayList;
    }

    //@CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/allMatches")
    public @ResponseBody Iterable<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    //@CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/matchId")
    public @ResponseBody
    Optional<Match> getMatchFromId(@RequestParam int id){
        return matchRepository.findById((long) id);
    }

    @Scheduled(cron = "0 0/14 * * * ?")
    public void updateMatchScore() throws IOException {
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date actualDate = new Date();
        ArrayList<Match> matchArrayList = (ArrayList<Match>) matchRepository.findAll();
        url = new URL("http://api.football-data.org/v1/competitions/467/fixtures");
        scanner = new Scanner(url.openStream());
        json = scanner.nextLine();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("fixtures");

        for (int i =0; i<matchArrayList.size(); i++){
            JSONObject jsonMatch = jsonArray.getJSONObject(i);
            JSONObject jsonMatchResult = jsonArray.getJSONObject(i).getJSONObject("result");
            try {
                Date matchDate = actualDateFormat.parse(matchArrayList.get(i).getMatchDateAndTime());

                if(matchDate.before(actualDate) && (matchArrayList.get(i).getHomeTeamScore() < 0)){
                    match.setId(matchArrayList.get(i).getId());
                    match.setHomeTeamId(matchArrayList.get(i).getHomeTeamId());
                    match.setAwayTeamId(matchArrayList.get(i).getAwayTeamId());
                    for(int j=0; j<32; j++) {
                        if(matchArrayList.get(j).getHomeTeamId().getName().equals(jsonMatch.getString("homeTeamName"))) {
                            match.setHomeTeamScore(Integer.parseInt(jsonMatchResult.get("goalsHomeTeam").toString()));
                        }
                    }
                    for(int k=0; k<32; k++) {
                        if (matchArrayList.get(k).getAwayTeamId().getName().equals(jsonMatch.getString("awayTeamName"))) {
                            match.setAwayTeamScore(Integer.parseInt(jsonMatchResult.get("goalsAwayTeam").toString()));
                        }
                    }
                    match.setMatchDateAndTime(matchArrayList.get(i).getMatchDateAndTime());
                    matchRepository.save(match);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
