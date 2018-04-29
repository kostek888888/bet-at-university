package bet_at_university.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// @RestController if You want to make REST only endpoint, otherwise if Thymeleaf is needed use @Controller
@Controller
public class Test {

    private String message = "Spring boot test with war";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }


//    You can use one of the following:
//
//    @RequestMapping("/")
//    public String welcome(Map<String, Object> model) {
//        model.put("message", this.message);
//        return "index";
//    }
//
//
//    @RequestMapping("/")
//    public String welcome(Model model) {
//        model.addAttribute("message", message);
//        return "index";
//    }

}
