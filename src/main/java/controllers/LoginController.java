package controllers;

import beans.User;
import forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.LoginService;
import service.UserService;


import static utils.Constants.REDIRECT_PREFIX;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "/login";
    private static final String REGISTRATION_PAGE = "/registration";
    private static final String WELCOME_PAGE = "/welcome";
    private static final String REGISTRATION_FORM_MODEL_ATTRIBUTE = "registrationForm";

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;


    @GetMapping(value = "/registration")
    public String openRegistrationPage(Model model) {
        model.addAttribute(REGISTRATION_FORM_MODEL_ATTRIBUTE, new RegistrationForm());
        return REGISTRATION_PAGE;
    }

    @PostMapping(value = "/registration")
    public String registerUser(@ModelAttribute(REGISTRATION_FORM_MODEL_ATTRIBUTE) RegistrationForm registrationForm, Model model) {
        User newUser = userService.createNewRegisteredUser(registrationForm);
        userService.saveUserWithRole(newUser);
        loginService.autoLogin(registrationForm.getName(), registrationForm.getPassword());

        return REDIRECT_PREFIX + WELCOME_PAGE;
    }

    @GetMapping(value = {"/", "/login"})
    public String openLoginPage(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "invalid credentials");
        }
        if (logout != null) {
            model.addAttribute("message", "success logout");
        }
        return LOGIN_PAGE;
    }

}