package bet_at_university.startupData;

import bet_at_university.database.model.BetRate;
import bet_at_university.database.model.Match;
import bet_at_university.database.repository.BetRateRepository;
import bet_at_university.database.repository.MatchRepository;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BetRateData {


    private ArrayList<Match> matchArrayList = new ArrayList<>();
    private ArrayList<Double> rateArrayList = new ArrayList<>();


    public void addBetRate(BetRateRepository betRateRepository, MatchRepository matchRepository) throws IOException {
        BetRate betRate = new BetRate();
        matchArrayList = (ArrayList<Match>) matchRepository.findAll();

        for(int i=0; i<matchArrayList.size(); i++) {
            rateArrayList = calculateRate(
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getWonMatches(),
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getLostMatches(),
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getDrawMatches(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getWonMatches(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getLostMatches(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getDrawMatches());
            betRate.setId(i+1);
            betRate.setHomeTeamWinRate(rateArrayList.get(0));
            betRate.setAwayTeamWinRate(rateArrayList.get(1));
            betRate.setDrawRate(rateArrayList.get(2));
            betRate.setHomeTeamWinOrDrawRate(rateArrayList.get(3));
            betRate.setAwayTeamWinOrDrawRate(rateArrayList.get(4));
            betRate.setHomeWinOrAwayWin(rateArrayList.get(5));
            betRateRepository.save(betRate);
        }

    }


    public ArrayList<Double> calculateRate(int homeWon, int homeLost, int homeDraw, int awayWon, int awayLost, int awayDraw){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        double homeWonRate = 4-(0.1*(homeWon+homeLost)/2);
        double AwayWonRate = 4-(0.1*(awayWon+awayLost)/2);
        double drawRate = (0.1*(homeDraw/awayDraw+2)/2);
        double homeWonOrDraw = (0.1*(homeWon+homeDraw)/2);
        double awayWonOrDraw = (0.1*(awayWon+awayDraw)/2);
        double homeOrAwayWon = (0.1*(homeWon+awayWon)/2);

        ArrayList<Double> matchRate = new ArrayList<>();
        matchRate.add(Double.valueOf(decimalFormat.format(homeWonRate)));
        matchRate.add(Double.valueOf(decimalFormat.format(AwayWonRate)));
        matchRate.add(Double.valueOf(decimalFormat.format(drawRate)));
        matchRate.add(Double.valueOf(decimalFormat.format(homeWonOrDraw)));
        matchRate.add(Double.valueOf(decimalFormat.format(awayWonOrDraw)));
        matchRate.add(Double.valueOf(decimalFormat.format(homeOrAwayWon)));

        return matchRate;
    }
}
