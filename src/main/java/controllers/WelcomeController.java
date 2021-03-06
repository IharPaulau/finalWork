package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private static final String WELCOME_PAGE = "welcome";

    @GetMapping()
    public String showWelcomePage() {
        return WELCOME_PAGE;
    }
}