package controllers;

import models.Car;
import models.Order;
import enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CarService;
import org.springframework.validation.BindingResult;
import service.OrderService;

import javax.validation.Valid;
import java.util.List;

import static utils.Constants.CAR_FORM_PAGE;
import static utils.Constants.CAR_MODEL_ATTRIBUTE;
import static utils.Constants.FORM_ACTION_MODEL_ATTRIBUTE;
import static utils.Constants.PAGE_LABEL_MODEL_ATTRIBUTE;
import static utils.Constants.REDIRECT_PREFIX;
import static utils.Constants.VIEW_CARS_PAGE;

@Controller
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/cars/carForm")
    public String showForm(Model model) {
        model.addAttribute(CAR_MODEL_ATTRIBUTE, new Car());
        model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "add.new.car.form");
        model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/carForm");
        return "cars/carForm";
    }

    @PostMapping("/car/carForm")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
            model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "add.new.car.form");
            model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/carForm");
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "cars/carForm";
        }
        carService.save(car);
        return REDIRECT_PREFIX + VIEW_CARS_PAGE;
    }


    @GetMapping(VIEW_CARS_PAGE)
    public String viewCar(Model model) {
        List<Car> list = carService.getCars();
        List<Order> listOrders = orderService.getOrders();
        long uncheckedOrders = listOrders.stream().filter(x -> OrderStatus.NOT_VERIFIED.equals(x.getOrderStatus())).count();
        long returnOrders = listOrders.stream().filter(x -> OrderStatus.RETURN.equals(x.getOrderStatus())).count();
        long approvedOrders = listOrders.stream().filter(x -> OrderStatus.APPROVED.equals(x.getOrderStatus())).count();
        model.addAttribute("list", list);
        model.addAttribute("uncheckedOrders", uncheckedOrders);
        model.addAttribute("returnOrders", returnOrders);
        model.addAttribute("approvedOrders", approvedOrders);
        return "cars/viewCars";
    }

    @GetMapping("/cars/editCar/{id}")
    public String edit(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "edit.car.form");
        model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/editCar/" + car.getId());
        return CAR_FORM_PAGE;
    }

    @PostMapping("/cars/editCar/{id}")
    public String editSave(@Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
            model.addAttribute(PAGE_LABEL_MODEL_ATTRIBUTE, "edit.car.form");
            model.addAttribute(FORM_ACTION_MODEL_ATTRIBUTE, "/cars/editCar/" + car.getId());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return CAR_FORM_PAGE;
        }
        carService.update(car);
        return REDIRECT_PREFIX + VIEW_CARS_PAGE;
    }

    @GetMapping("/cars/carDetails/{id}")
    public String showCarDetails(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute(CAR_MODEL_ATTRIBUTE, car);
        return "cars/carInfo";
    }

    @GetMapping("/cars/deleteCar/{id}")
    public String delete(@PathVariable int id) {
        carService.delete(id);
        return REDIRECT_PREFIX + VIEW_CARS_PAGE;
    }
}