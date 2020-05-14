package controllers;


import beans.User;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.serviceImpl.UserServiceImpl;

import java.util.Objects;


@Controller
public class LoginController {

    MessageSource messageSource;
    UserServiceImpl userServiceImpl;

    @GetMapping("/login/startmenu")
    public String loginMenu() {

        return "login/startmenu";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("UserLogin") User userLogin, Model logModel) {
       User user = userServiceImpl.chekUser(userLogin.getLogin());
        if(!Objects.isNull(user)){

            logModel.addAttribute("user", user);
        }
        return "redirect:/login/mainmenu";


    }
}
