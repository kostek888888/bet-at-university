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
    Boolean registrationUser(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String birthDate,
                             @RequestParam String email, @RequestParam String address, @RequestParam String postCode, @RequestParam String login, @RequestParam String password) {

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
        user.setEmail(email);
        user.setAddress(address);
        user.setPostCode(postCode);
        user.setLogin(login);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUserStatistics(userStatisticsArrayList.get(userStatisticsArrayList.size() - 1));
        user.setFacebookId("0");
        try {
            userRepository.save(user);
            return Boolean.TRUE;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }





    // return true if cookie create (user is logged in), else return false. Front-end need this to check is user logged
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/logIn", value = "/logIn")
    public @ResponseBody
    Boolean logIn(HttpServletResponse response, @RequestParam String login, @RequestParam String password) {
        userArrayList = (ArrayList<User>) userRepository.findAll();
        response.setHeader("Access-Control-Allow-Credentials", "true"); // need to unlock response to resource (CORS)


        for (int i=0; i<userArrayList.size(); i++) {
            System.out.println(i);
            if (bCryptPasswordEncoder.matches(password, userArrayList.get(i).getPassword())
                    && login.equals(userArrayList.get(i).getLogin())) {

                Cookie cookie = new Cookie("bet_at_university_cookie",  Long.toString( userArrayList.get(i).getId() ) ); // matched userId is cookie value (userId number represents userId in db table)
                cookie.setMaxAge(60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(false);  // font-end can't read httpOnly = true cookies
                cookie.setSecure(false);
                try {
                    response.addCookie(cookie);
                    return Boolean.TRUE;
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return  Boolean.FALSE;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/logOut", value = "/logOut")
    public @ResponseBody Boolean logOut(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Cookie cookie = new Cookie("bet_at_university_cookie", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.print("wylogowany");
        return Boolean.TRUE;

    }


    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/checkUsers", value = "/checkUsers")
    public @ResponseBody Boolean checkUsers(HttpServletResponse response, @RequestParam String login) {
        ArrayList<User> arrayListUsers = new ArrayList<>();
        arrayListUsers = (ArrayList<User>) userRepository.findAll();
        for (int i = 0; i < arrayListUsers.size(); i++) {
            if (login.equals(arrayListUsers.get(i).getLogin())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }


    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/writeFacebookUserInDB")
    public @ResponseBody
    Boolean writeFacebookUserInDB(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String facebookId) {

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
        user.setEmail(email);
        user.setFacebookId(facebookId);
        user.setUserStatistics(userStatisticsArrayList.get(userStatisticsArrayList.size() - 1));
        try {
            userRepository.save(user);
            return Boolean.TRUE;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    // If user log in with Facebook first check Users FacebookId's in db. If nobody have FacebookId from param it means that the user is not yet saved in the database. Then write user in db by writeFacebookUserInDB()
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/checkUserByFacebookId", value = "/checkUserByFacebookId")
    public @ResponseBody Boolean checkUserByFacebookId(HttpServletResponse response, @RequestParam String facebookId) {
        ArrayList<User> arrayListUsers = new ArrayList<>();
        arrayListUsers = (ArrayList<User>) userRepository.findAll();
        for (int i = 0; i < arrayListUsers.size(); i++) {
            if (facebookId.equals(arrayListUsers.get(i).getFacebookId())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(path = "/loginFacebookUser", value = "/loginFacebookUser")
    public @ResponseBody
    Boolean loginFacebookUser(HttpServletResponse response, @RequestParam String facebookId) {
        userArrayList = (ArrayList<User>) userRepository.findAll();
        response.setHeader("Access-Control-Allow-Credentials", "true"); // need to unlock response to resource (CORS)
        for (int i=0; i<userArrayList.size(); i++) {
            if (facebookId.equals(userArrayList.get(i).getFacebookId()))
            {
                Cookie cookie = new Cookie("bet_at_university_cookie",  Long.toString( userArrayList.get(i).getId() ) ); // matched userId is cookie value (userId number represents userId in db table)
                cookie.setMaxAge(60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(false);  // font-end can't read httpOnly = true cookies
                cookie.setSecure(false);
                try {
                    response.addCookie(cookie);
                    return Boolean.TRUE;
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return  Boolean.FALSE;
    }

}
