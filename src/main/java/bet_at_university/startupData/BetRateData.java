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

        for (int i = 0; i < matchArrayList.size(); i++) {
            rateArrayList = calculateRate(
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getGoals(),
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getGoalDifference(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getGoals(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getGoalDifference(),
                    matchArrayList.get(i).getHomeTeamId().getTeamStatistics().getGoalsAgainst(),
                    matchArrayList.get(i).getAwayTeamId().getTeamStatistics().getGoalsAgainst());

            betRate.setId(i + 1);
            betRate.setHomeTeamWinRate(rateArrayList.get(0));
            betRate.setAwayTeamWinRate(rateArrayList.get(1));
            betRate.setDrawRate(rateArrayList.get(2));
            betRate.setHomeTeamWinOrDrawRate(rateArrayList.get(3));
            betRate.setAwayTeamWinOrDrawRate(rateArrayList.get(4));
            betRate.setHomeWinOrAwayWin(rateArrayList.get(5));
            betRateRepository.save(betRate);
        }

    }


    public ArrayList<Double> calculateRate(int homeGoals, int homeGoalsDifference, int awayGoals, int awayGoalsDifference, int homeGoalsAgainst, int awayGoalsAgainst) {


        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // "." for linux!!!
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        double homeWonRate = 4 - (0.175 * (homeGoals + homeGoalsDifference));
        improveRate(homeWonRate);
        double awayWonRate = 4 - (0.175 * (awayGoals + awayGoalsDifference));
        improveRate(awayWonRate);
        double drawRate = (0.175 * ((homeGoals + 1) / (homeGoalsAgainst + 1)) + ((awayGoals + 1) / (awayGoalsAgainst + 1)));
        improveRate(drawRate);
        double homeWonOrDraw = drawRate - homeWonRate;
        improveRate(homeWonOrDraw);
        double awayWonOrDraw = drawRate - awayWonRate;
        improveRate(awayWonOrDraw);
        double homeOrAwayWon = (homeWonOrDraw + awayWonOrDraw) / 2;
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


    public double improveRate(double rate) {
        Random random = new Random();
        if (rate <= 1 && rate >= -1) {
            rate = rate + 1.5 + random.nextDouble();
        }
        if (rate < -1) {
           rate = Math.abs(rate);
        }

        return rate;
    }

}
