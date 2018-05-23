package bet_at_university.controllers;

import bet_at_university.database.model.Match;
import bet_at_university.database.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/match")
    public @ResponseBody Iterable<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(path = "/matchId")
    public @ResponseBody
    Optional<Match> getMatchFromId(@RequestParam int id){
        return matchRepository.findById((long) id);
    }
}
