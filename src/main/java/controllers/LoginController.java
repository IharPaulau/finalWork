package controllers;

import beans.User;
import forms.RegistrationForm;
import service.LoginService;
import service.UserService;
import validators.RegistrationFormValidator;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    @GetMapping("/registration")
    public String openRegistrationPage(Model model) {
        model.addAttribute(REGISTRATION_FORM_MODEL_ATTRIBUTE, new RegistrationForm());
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute(REGISTRATION_FORM_MODEL_ATTRIBUTE) @Valid RegistrationForm registrationForm,
                               BindingResult bindingResult, Model model) {

        registrationFormValidator.validate(registrationForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute(REGISTRATION_FORM_MODEL_ATTRIBUTE, registrationForm);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return REGISTRATION_PAGE;
        }

        User newUser = userService.createNewRegisteredUser(registrationForm);
        userService.saveUserWithRole(newUser);
        loginService.autoLogin(registrationForm.getName(), registrationForm.getPassword());

        return REDIRECT_PREFIX + WELCOME_PAGE;
    }

    @GetMapping(value = {"/", "/login"})
    public String openLoginPage(Model model, String error, String logout, Locale locale) {
        if (error != null) {
            model.addAttribute("error", messageSource.getMessage("invalid.credentials", null, locale));
        }
        if (logout != null) {
            model.addAttribute("message", messageSource.getMessage("success.logout", null, locale));
        }
        return LOGIN_PAGE;
    }
}