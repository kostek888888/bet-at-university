package bet_at_university.controllers;

import bet_at_university.database.model.Match;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.startupData.MatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping(path = "/")
public class MatchController {
    private Match match = new Match();
    private Random random = new Random();
    private ArrayList<Match> matchArrayList = new ArrayList<>();
    private ArrayList<Match> matchToBetArrayList = new ArrayList<>();


    @Autowired
    private MatchRepository matchRepository;

    @CrossOrigin(origins="http://localhost:4200")
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

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/allMatches")
    public @ResponseBody Iterable<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/matchId")
    public @ResponseBody
    Optional<Match> getMatchFromId(@RequestParam int id){
        return matchRepository.findById((long) id);
    }

    @Scheduled(cron = "0 0/14 * * * ?")
    public void updateMatchScore(){
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date actualDate = new Date();
        ArrayList<Match> matchArrayList = (ArrayList<Match>) matchRepository.findAll();

        for (int i =0; i<matchArrayList.size(); i++){
            try {
                Date matchDate = actualDateFormat.parse(matchArrayList.get(i).getMatchDateAndTime());

                if(matchDate.before(actualDate) && matchArrayList.get(i).getHomeTeamScore() < 0){
                    match.setId(matchArrayList.get(i).getId());
                    match.setHomeTeamId(matchArrayList.get(i).getHomeTeamId());
                    match.setAwayTeamId(matchArrayList.get(i).getAwayTeamId());
                    match.setHomeTeamScore(random.nextInt(5));
                    match.setAwayTeamScore(random.nextInt(5));
                    match.setMatchDateAndTime(matchArrayList.get(i).getMatchDateAndTime());
                    matchRepository.save(match);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
