package bet_at_university.controllers;

import bet_at_university.database.model.UserStatistics;
import bet_at_university.database.repository.UserStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class UserStatisticsController {
    private ArrayList<UserStatistics> userStatisticsArrayList = new ArrayList<>();

    @Autowired
    private UserStatisticRepository userStatisticRepository;


    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/allUsersStatistics")
    public @ResponseBody Iterable<UserStatistics> getAllUserStatistics(){
        return userStatisticRepository.findAll();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/userStatisticsFromId")
    public @ResponseBody
    Optional<UserStatistics> getUserStatisticsFromId( @RequestParam long id){
        return userStatisticRepository.findById(id);
    }


    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/checkAccountBalance")
    public @ResponseBody double checkAccountBalance(@RequestParam long userId){
        userStatisticsArrayList = (ArrayList<UserStatistics>) userStatisticRepository.findAll();
        return userStatisticsArrayList.get((int) userId-1).getAccountBalance();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/pay")
    public @ResponseBody  Optional<UserStatistics> pay(@RequestParam long userId, @RequestParam double money){
        UserStatistics userStatistics = new UserStatistics();
        userStatisticsArrayList = (ArrayList<UserStatistics>) userStatisticRepository.findAll();
        userStatistics.setId(userId);
        userStatistics.setAccountBalance(userStatisticsArrayList.get((int) userId-1).getAccountBalance() + money);
        userStatistics.setWonMatches(userStatisticsArrayList.get((int) userId-1).getWonMatches());
        userStatistics.setLostMatches(userStatisticsArrayList.get((int) userId-1).getLostMatches());
        userStatistics.setBiggestWinDate(userStatisticsArrayList.get((int) userId-1).getBiggestWinDate());
        userStatistics.setBiggestWin(userStatisticsArrayList.get((int) userId-1).getBiggestWin());
        userStatisticRepository.save(userStatistics);
        return userStatisticRepository.findById(userId);
    }
}
