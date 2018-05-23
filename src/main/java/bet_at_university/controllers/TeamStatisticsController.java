package bet_at_university.controllers;

import bet_at_university.database.model.TeamStatistics;
import bet_at_university.database.repository.TeamStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/")
public class TeamStatisticsController {


    @Autowired
    private TeamStatisticsRepository teamStatisticsRepository;


    @GetMapping(path = "/teamStatistics")
    public @ResponseBody Iterable<TeamStatistics> getAllTeamStatistics(){
        return teamStatisticsRepository.findAll();
    }
}
