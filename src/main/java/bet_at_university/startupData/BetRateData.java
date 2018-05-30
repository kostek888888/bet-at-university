package bet_at_university.startupData;

import bet_at_university.database.model.BetRate;
import bet_at_university.database.model.Match;
import bet_at_university.database.repository.BetRateRepository;
import bet_at_university.database.repository.MatchRepository;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

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


        DecimalFormat decimalFormat = new DecimalFormat("#,##"); // "." for linux!!!
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        double homeWonRate = 4-(0.1*(homeWon+homeLost)/2);
        improveRate(homeWonRate);
        double awayWonRate = 4-(0.1*(awayWon+awayLost)/2);
        improveRate(awayWonRate);
        double drawRate = (0.1*(homeDraw/awayDraw+2)/2);
        improveRate(drawRate);
        double homeWonOrDraw = (0.1*(homeWon+homeDraw)/2);
        improveRate(homeWonOrDraw);
        double awayWonOrDraw = (0.1*(awayWon+awayDraw)/2);
        improveRate(awayDraw);
        double homeOrAwayWon = (0.1*(homeWon+awayWon)/2);
        improveRate(homeOrAwayWon);

        ArrayList<Double> matchRate = new ArrayList<>();
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(homeWonRate))));
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(awayWonRate))));
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(drawRate))));
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(homeWonOrDraw))));
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(awayWonOrDraw))));
        matchRate.add(Double.valueOf(decimalFormat.format(improveRate(homeOrAwayWon))));

        return matchRate;
    }


    public double improveRate(double rate){
        Random random = new Random();
        if(rate<=1){
            rate = rate + 1+random.nextDouble();
        }
        return rate;
    }
}
