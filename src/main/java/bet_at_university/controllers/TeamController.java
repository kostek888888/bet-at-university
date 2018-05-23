package bet_at_university.controllers;

import bet_at_university.database.model.Team;
import bet_at_university.database.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(path = "/team")
    public @ResponseBody Iterable<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
