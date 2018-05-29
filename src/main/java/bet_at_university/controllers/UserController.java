package bet_at_university.controllers;


import bet_at_university.database.model.User;
import bet_at_university.database.model.UserStatistics;
import bet_at_university.database.repository.UserRepository;
import bet_at_university.database.repository.UserStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/")
public class UserController {
    private ArrayList<UserStatistics> userStatisticsArrayList = new ArrayList<>();
    private ArrayList<User> userArrayList = new ArrayList<>();
    private User user = new User();


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStatisticRepository userStatisticRepository;


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/register")
    public @ResponseBody
    void registrationUser(@RequestParam String name, String surname, String birthDate,
                          String address, String postCode, String login, String password) {

        User user = new User();
        UserStatistics userStatistics = new UserStatistics();

        userStatistics.setAccountBalance(0);
        userStatistics.setBiggestWin(0);
        userStatistics.setBiggestWinDate("-");
        userStatistics.setWonMatches(0);
        userStatistics.setLostMatches(0);
        userStatisticRepository.save(userStatistics);

        userStatisticsArrayList = (ArrayList<UserStatistics>) userStatisticRepository.findAll();

        user.setName(name);
        user.setSurname(surname);
        user.setBirthDate(birthDate);
        user.setAddress(address);
        user.setPostCode(postCode);
        user.setLogin(login);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUserStatistics(userStatisticsArrayList.get(userStatisticsArrayList.size() - 1));
        userRepository.save(user);
    }

    // return userId in db if cookie create (user is logged in), else return false. Front-end need this to check is user logged
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/logIn", value = "/logIn")
    public @ResponseBody
    Long logIn(HttpServletResponse response, @RequestParam String login, @RequestParam String password) {
        userArrayList = (ArrayList<User>) userRepository.findAll();
        response.setHeader("Access-Control-Allow-Credentials", "true"); // need to unlock response to resource (CORS)

        for (int i=0; i<userArrayList.size(); i++) {
            System.out.println(i);
            if (bCryptPasswordEncoder.matches(password, userArrayList.get(i).getPassword())
                    && login.equals(userArrayList.get(i).getLogin())) {

                Cookie cookie = new Cookie("bet_at_university_cookie",  Long.toString( userArrayList.get(i).getId() ) ); // matched userId is cookie value (userId number represents userId in db table)
                cookie.setMaxAge(60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setSecure(false);
                try {
                    response.addCookie(cookie);
//                    System.out.println("zalogowany: login: " + login + " pass: " + password);
//                    System.out.println("Response contentType " + response.getContentType());
//                    System.out.println("Status żądania " + response.getStatus());
                    return userArrayList.get(i).getId();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/logOut")
    public void logOut(HttpServletResponse response){
        Cookie cookie = new Cookie("bet_at_university_cookie", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
