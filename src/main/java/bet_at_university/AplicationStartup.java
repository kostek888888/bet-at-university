package bet_at_university;


import bet_at_university.database.repository.BetRateRepository;
import bet_at_university.database.repository.MatchRepository;
import bet_at_university.database.repository.TeamRepository;
import bet_at_university.database.repository.TeamStatisticsRepository;
import bet_at_university.startupData.BetRateData;
import bet_at_university.startupData.JsonParserTeam;
import bet_at_university.startupData.JsonParserTeamStatistics;
import bet_at_university.startupData.MatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    TeamStatisticsRepository teamStatisticsRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    private BetRateRepository betRateRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

         JsonParserTeamStatistics jsonParserTeamStatistics = new JsonParserTeamStatistics();
        JsonParserTeam jsonParserTeam = new JsonParserTeam();
        MatchData matchData = new MatchData();
        BetRateData betRateData = new BetRateData();
        try {
            jsonParserTeamStatistics.addTeamStatistics(teamStatisticsRepository);
            jsonParserTeam.addTeam(teamRepository, teamStatisticsRepository);
            matchData.addMatch(matchRepository, teamRepository);
            betRateData.addBetRate(betRateRepository, matchRepository);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
