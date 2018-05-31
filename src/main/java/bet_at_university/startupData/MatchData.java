package bet_at_university.startupData;

import bet_at_university.database.model.Match;
import bet_at_university.database.model.Team;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.database.repository.TeamRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MatchData {

    private ArrayList<Team> teamArrayList = new ArrayList<>();
    private Random random = new Random();
    private DateFormat actualDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Date actualDate = new Date();

    public void addMatch( MatchRepository matchRepository, TeamRepository teamRepository) {
        Match match = new Match();
        teamArrayList = (ArrayList<Team>) teamRepository.findAll();

        for(int i=0; i<5; i++){
            DateFormat matchDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date matchDate = null;
            try {
                matchDate = matchDateFormat.parse("2018/"+addZeroToDate(random.nextInt(11)+1)+"/"+addZeroToDate(random.nextInt(30)+1)+" 09:"+i+"1");
//                matchDate = matchDateFormat.parse("2018/05/31 11:"+i+""+i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            match.setId(i+1);
            match.setHomeTeamId(teamArrayList.get(random.nextInt(9)+1));
            match.setAwayTeamId(teamArrayList.get(random.nextInt(10)+9));
            match.setMatchDateAndTime(matchDateFormat.format(matchDate));

            if(matchDate.before(actualDate)){
                match.setHomeTeamScore(random.nextInt(5));
                match.setAwayTeamScore(random.nextInt(5));

            }
            else {
                match.setHomeTeamScore(-1);
                match.setAwayTeamScore(-1);
            }
            matchRepository.save(match);
        }

    }

    public String addZeroToDate(int date){
        String newDate;

        if(date < 10){
            newDate = "0" + date;
            return newDate;
        }
        else {
            return String.valueOf(date);
        }
    }





}
