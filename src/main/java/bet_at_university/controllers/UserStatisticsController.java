package bet_at_university.controllers;

import bet_at_university.database.model.UserStatistics;
import bet_at_university.database.repository.UserStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class UserStatisticsController {

    @Autowired
    private UserStatisticRepository userStatisticRepository;

    @PostMapping(path = "/addUS")
    public @ResponseBody void addUserStatistics(@RequestParam int accountBalance, double biggestWin,
                                                String biggestWinDate, int wonOrLost){
        UserStatistics userStatistics = new UserStatistics();

        userStatistics.setAccountBalance(accountBalance);
        userStatistics.setBiggestWin(biggestWin);
        userStatistics.setBiggestWinDate(biggestWinDate);
        if(wonOrLost == 0){
            userStatistics.setWonMatches(userStatistics.getWonMatches()+1);
        }
        else {
            userStatistics.setLostMatches(userStatistics.getLostMatches()+1);
        }
        userStatisticRepository.save(userStatistics);
    }


    @GetMapping(path = "/allUsersStatistics")
    public @ResponseBody Iterable<UserStatistics> getAllUserStatistics(){
        return userStatisticRepository.findAll();
    }

    @GetMapping(path = "/userStatisticsFromId")
    public @ResponseBody
    Optional<UserStatistics> getUserStatisticsFromId(@RequestParam long id){
        return userStatisticRepository.findById(id);
    }
}
