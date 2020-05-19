package controllers;


import beans.Car;
import beans.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.CarService;
import service.OrderService;

import java.util.List;

import static utils.Constants.*;

@Controller
public class OrderController {

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/makeOrder/{id}")
    public String showForm(@PathVariable int id, Model model) {
        model.addAttribute(ORDER_MODEL_ATTRIBUTE, new Order());
        model.addAttribute("carId", id);
        return "orders/orderForm";
    }

    @PostMapping("/saveOrder")
    public String save(@ModelAttribute(ORDER_MODEL_ATTRIBUTE) Order order) {
        orderService.save(order);
        return REDIRECT_PREFIX + "/orders/viewMyOrders";
    }


    @GetMapping("/orders/viewMyOrders")
    public String viewMyOrders(Model model) {
        List<Order> list = orderService.getOwnOrders();
        model.addAttribute("list", list);
        return "orders/viewMyOrders";
    }

    @GetMapping("/orders/viewOrders")
    public String viewAllOrders(Model model) {
        List<Order> list = orderService.getOrders();
        model.addAttribute("list", list);
        return "orders/viewOrders";
    }

    @GetMapping(value = "/orders/deleteMyOrder/{id}")
    public String delete(@PathVariable int id) {
        orderService.delete(id);
        return REDIRECT_PREFIX + "/orders/viewMyOrders";
    }



}
