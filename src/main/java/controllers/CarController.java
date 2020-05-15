package controllers;

import beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CarService;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/cars/carform")
    public String showform(Model m) {
        m.addAttribute("car", new Car());
        return "cars/carform";
    }

       @PostMapping(value = "/save")
    public String save(@ModelAttribute("car") Car car) {
        carService.save(car);
        return "redirect:/cars/viewcars";
    }


    @GetMapping("/cars/viewcars")
    public String viewemp(Model m) {
        List<Car> list = carService.getCars();
        m.addAttribute("list", list);
        return "cars/viewcars";
    }

      @GetMapping(value = "/cars/editcar/{id}")
    public String edit(@PathVariable int id, Model m) {
        Car car = carService.getCarById(id);
        m.addAttribute("car", car);
        return "cars/careditform";
    }

    @GetMapping(value = "/cars/cardetails/{id}")
    public String carinfo(@PathVariable int id, Model m) {
        Car car = carService.getCarById(id);
        m.addAttribute("car", car);
        return "cars/carinfo";
    }



    @PostMapping(value = "/cars/editsave")
    public String editsave(@ModelAttribute("car") Car car) {
        carService.update(car);
        return "redirect:/cars/viewcars";
    }


    @GetMapping(value = "/cars/deletecar/{id}")
    public String delete(@PathVariable int id) {
        carService.delete(id);
        return "redirect:/cars/viewcars";
    }
}