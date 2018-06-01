package bet_at_university.controllers;

import bet_at_university.database.model.UserStatistics;
import bet_at_university.database.repository.UserStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class UserStatisticsController {

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
}
