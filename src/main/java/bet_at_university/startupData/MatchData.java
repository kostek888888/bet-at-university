package bet_at_university.startupData;

import bet_at_university.database.model.Match;
import bet_at_university.database.model.Team;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.database.repository.TeamRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MatchData {

    private ArrayList<Team> teamArrayList = new ArrayList<>();
    private Random random = new Random();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public void addMatch( MatchRepository matchRepository, TeamRepository teamRepository){
        Match match = new Match();
        teamArrayList = (ArrayList<Team>) teamRepository.findAll();

        for(int i=0; i<5; i++){
            match.setId(i+1);
            match.setHomeTeamId(teamArrayList.get(random.nextInt(9)+1));
            match.setAwayTeamId(teamArrayList.get(random.nextInt(10)+9));
            match.setHomeTeamScore(random.nextInt(5));
            match.setAwayTeamScore(random.nextInt(5));
            match.setMatchDateAndTime(dateFormat.format(date));
            matchRepository.save(match);
        }

    }
}
