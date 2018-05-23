package bet_at_university.controllers;

import bet_at_university.database.model.Team;
import bet_at_university.database.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/team")
    public @ResponseBody Iterable<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
