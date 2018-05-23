package bet_at_university.controllers;

import bet_at_university.database.model.Match;
import bet_at_university.database.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping(path = "/match")
    public @ResponseBody Iterable<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    @GetMapping(path = "/matchId")
    public @ResponseBody
    Optional<Match> getMatchFromId(@RequestParam int id){
        return matchRepository.findById((long) id);
    }
}
