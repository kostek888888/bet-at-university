package bet_at_university.controllers;


import bet_at_university.database.model.BetRate;
import bet_at_university.database.repository.BetRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/")
public class BetRateController {

    @Autowired
    private BetRateRepository betRateRepository;


    @GetMapping(path = "/betRate")
    public @ResponseBody Iterable<BetRate> getAllBetRate(){
        return betRateRepository.findAll();
    }


}
