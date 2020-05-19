package controllers;

import beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CarService;

import java.util.List;

import static utils.Constants.CAR_MODEL_ATTRIBUTE;
import static utils.Constants.REDIRECT_PREFIX;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars/carForm")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/carForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(CAR_MODEL_ATTRIBUTE) Car car) {
        carService.save(car);
        return REDIRECT_PREFIX + "/cars/viewCars";
    }


    @GetMapping("/cars/viewCars")
    public String viewCar(Model model) {
        List<Car> list = carService.getCars();
        model.addAttribute("list", list);
        return "cars/viewCars";
    }

    @GetMapping("/cars/editCar/{id}")
    public String edit(@PathVariable int id, Model m) {
        Car car = carService.getCarById(id);
        m.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        return "cars/carEditForm";
    }

    @GetMapping("/cars/carDetails/{id}")
    public String showCarDetails(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        return "cars/carInfo";
    }


    @PostMapping(value = "/cars/editSave")
    public String editSave(@ModelAttribute(CAR_MODEL_ATTRIBUTE) Car car) {
        carService.update(car);
        return REDIRECT_PREFIX + "/cars/viewCars";
    }


    @GetMapping(value = "/cars/deleteCar/{id}")
    public String delete(@PathVariable int id) {
        carService.delete(id);
        return REDIRECT_PREFIX + "/cars/viewCars";
    }
}