package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MainController {

    @GetMapping("login/a")
    public ModelAndView main() {
        return new ModelAndView("test_welcomePage");

    }
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username or password!");
//        }
//        model.setViewName("welcomePage");
//        return model;
//
//    }


        @RequestMapping(value = "/second")
        public String indexPage () {
            return "secondPage";
        }

        @RequestMapping(value = "/admin")
        public String adminPage () {
            return "adminPage";
        }
}