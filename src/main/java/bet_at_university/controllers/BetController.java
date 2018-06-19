package bet_at_university.controllers;


import bet_at_university.database.model.*;
import bet_at_university.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


@Controller
@RequestMapping(path = "/")
public class BetController {
    private ArrayList<Match> matchArrayList = new ArrayList<>();
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ArrayList<BetRate> betRateArrayList = new ArrayList<>();
    private ArrayList<Bet> betArrayList = new ArrayList<>();
    private ArrayList<Bet> betArrayListSize = new ArrayList<>();
    private ArrayList<Bet> userBetArrayList = new ArrayList<>();

    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    BetRateRepository betRateRepository;

    @Autowired
    UserStatisticRepository userStatisticRepository;

//    betResult:
//    0 - Lost
//    1 - Winning
//    2 - not played yet
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/betMatch")
    public @ResponseBody
    Boolean betMatch(@RequestParam long matchId, @RequestParam long userId, @RequestParam String betType,
                    @RequestParam double moneyInserted) {
        Bet bet = new Bet();
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setId(userId);
        matchArrayList = (ArrayList<Match>) matchRepository.findAll();
        userArrayList = (ArrayList<User>) userRepository.findAll();
        betRateArrayList = (ArrayList<BetRate>) betRateRepository.findAll();
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date actualDate = new Date();

            bet.setMatches(matchArrayList.get((int) matchId - 1));
            bet.setUser(userArrayList.get((int) userId - 1));
            bet.setMoneyInserted(moneyInserted);
            bet.setBetRate(betRateArrayList.get((int) matchId - 1));


            switch (betType) {
                case "1":
                    bet.setBetType("1");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getHomeTeamWinRate()));
                    break;

                case "2":
                    bet.setBetType("2");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getAwayTeamWinRate()));
                    break;

                case "X":
                    bet.setBetType("X");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getDrawRate()));
                    break;

                case "1X":
                    bet.setBetType("1X");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getHomeTeamWinOrDrawRate()));
                    break;

                case "2X":
                    bet.setBetType("2X");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getAwayTeamWinOrDrawRate()));
                    break;

                case "12":
                    bet.setBetType("12");
                    bet.setAmountToPaidOut(amountToPaidOutWithTax(moneyInserted, bet.getBetRate().getHomeWinOrAwayWin()));
                    break;
            }
            bet.setBuyDateAndTime(actualDate.toString());
            bet.setBetResult(2);
            userStatistics.setAccountBalance(userArrayList.get((int) userId-1).getUserStatistics().getAccountBalance()-moneyInserted);


            userStatisticRepository.save(userStatistics);
            betRepository.save(bet);


            return Boolean.TRUE ;
    }

    public double amountToPaidOutWithTax(double moneyInserted, double betRate) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##" , new DecimalFormatSymbols(Locale.UK)); // del sec param for linux!!!
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(decimalFormat.format(moneyInserted * betRate - (0.12 * moneyInserted * betRate)));
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/userBet")
    public @ResponseBody
    ArrayList<Bet> userBet(@RequestParam long userId) {
        userBetArrayList.clear();
        betArrayList = (ArrayList<Bet>) betRepository.findAll();
        for (int i = 0; i < betArrayList.size(); i++) {
            if (betArrayList.get(i).getUser().getId() == userId) {
                userBetArrayList.add(betArrayList.get(i));
            }
        }
        return userBetArrayList;
    }


    @Scheduled(cron = "* 0/30 * * * ?")
    public void checkBet() {
        Bet bet = new Bet();
        UserStatistics userStatistics = new UserStatistics();
        betArrayListSize = (ArrayList<Bet>) betRepository.findAll();
        for (int i = 0; i < betArrayListSize.size(); i++) {
            betArrayList = (ArrayList<Bet>) betRepository.findAll();
            if (betArrayList.get(i).getMatches().getAwayTeamScore() >= 0 && betArrayList.get(i).getBetResult() == 2) {
                bet.setId(i + 1);
                bet.setAmountToPaidOut(betArrayList.get(i).getAmountToPaidOut());
                bet.setUser(betArrayList.get(i).getUser());
                bet.setBetType(betArrayList.get(i).getBetType());
                bet.setMatches(betArrayList.get(i).getMatches());
                bet.setMoneyInserted(betArrayList.get(i).getMoneyInserted());
                bet.setAmountToPaidOut(betArrayList.get(i).getAmountToPaidOut());
                bet.setBetRate(betArrayList.get(i).getBetRate());
                bet.setBuyDateAndTime(betArrayList.get(i).getBuyDateAndTime());

                switch (betArrayList.get(i).getBetType()) {
                    case "1":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() > betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;

                    case "2":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() < betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;

                    case "X":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() == betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;

                    case "1X":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() >= betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;

                    case "2X":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() <= betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;

                    case "12":
                        if (betArrayList.get(i).getMatches().getHomeTeamScore() != betArrayList.get(i).getMatches().getAwayTeamScore()) {
                            betResultWin(betArrayList, bet, userStatistics, i);
                        }
                        else {
                            betResultLost(betArrayList, bet, userStatistics, i);
                        }
                        break;
                }
                betRepository.save(bet);
                userStatisticRepository.save(userStatistics);
            }
        }
    }

        public void betResultWin (ArrayList < Bet > betArrayList, Bet bet, UserStatistics userStatistics, int i){

            bet.setBetResult(1);
            userStatistics.setId(betArrayList.get(i).getUser().getUserStatistics().getId());
            userStatistics.setLostMatches(betArrayList.get(i).getUser().getUserStatistics().getLostMatches());
            userStatistics.setWonMatches(betArrayList.get(i).getUser().getUserStatistics().getWonMatches()+1);
            userStatistics.setAccountBalance(betArrayList.get(i).getUser().getUserStatistics().getAccountBalance() + betArrayList.get(i).getAmountToPaidOut());
            if (betArrayList.get(i).getUser().getUserStatistics().getBiggestWin() < betArrayList.get(i).getAmountToPaidOut()) {
                userStatistics.setBiggestWin(betArrayList.get(i).getAmountToPaidOut());
                userStatistics.setBiggestWinDate(betArrayList.get(i).getBuyDateAndTime());
            } else {
                userStatistics.setBiggestWin(betArrayList.get(i).getUser().getUserStatistics().getBiggestWin());
                userStatistics.setBiggestWinDate(betArrayList.get(i).getUser().getUserStatistics().getBiggestWinDate());
            }


        }

        public void betResultLost(ArrayList < Bet > betArrayList, Bet bet, UserStatistics userStatistics, int i){

            bet.setBetResult(0);
            userStatistics.setId(betArrayList.get(i).getUser().getUserStatistics().getId());
            userStatistics.setLostMatches(betArrayList.get(i).getUser().getUserStatistics().getLostMatches()+1);
            userStatistics.setWonMatches(betArrayList.get(i).getUser().getUserStatistics().getWonMatches());
            userStatistics.setAccountBalance(betArrayList.get(i).getUser().getUserStatistics().getAccountBalance());
            userStatistics.setBiggestWin(betArrayList.get(i).getUser().getUserStatistics().getBiggestWin());
            userStatistics.setBiggestWinDate(betArrayList.get(i).getUser().getUserStatistics().getBiggestWinDate());
        }
}

