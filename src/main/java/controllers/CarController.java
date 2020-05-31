package controllers;

import beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CarService;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

import static utils.Constants.CAR_MODEL_ATTRIBUTE;
import static utils.Constants.FORM_ACTION_MODEL_ATTRIBUTE;
import static utils.Constants.PAGE_LABEL_MODEL_ATTRIBUTE;
import static utils.Constants.REDIRECT_PREFIX;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars/carForm")
    public String showForm(Model model) {
        model.addAttribute(CAR_MODEL_ATTRIBUTE, new Car());
        model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "add.new.car.form");
        model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/carForm");
        return "cars/carForm";
    }

    @PostMapping("/car/carForm")
    public String save(@ModelAttribute(CAR_MODEL_ATTRIBUTE) @Valid Car car,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
            model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "add.new.car.form");
            model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/carForm");
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "cars/carForm";
        }
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
    public String edit(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "edit.car.form");
        model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/editCar/" + car.getId());
        return "cars/carForm";

    }

    @GetMapping("/cars/carDetails/{id}")
    public String showCarDetails(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        return "cars/carInfo";
    }


    @PostMapping(value = "/cars/editCar/{id}")
    public String editSave(@PathVariable int id, @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
            model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "edit.car.form");
            model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/editCar/" + car.getId());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "cars/carForm";
        }

        carService.update(car);
        return REDIRECT_PREFIX + "/cars/viewCars";
    }


    @GetMapping(value = "/cars/deleteCar/{id}")
    public String delete(@PathVariable int id) {
        carService.delete(id);
        return REDIRECT_PREFIX + "/cars/viewCars";
    }
}