package controllers;

import beans.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.CarService;
import service.OrderService;


import javax.validation.Valid;
import java.util.List;

import static utils.Constants.*;

@Controller
public class OrderController {

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderForm/{id}")
    public String showForm(@PathVariable int id, Model model) {
        model.addAttribute(ORDER_MODEL_ATTRIBUTE, new Order());
        model.addAttribute("carId", id);
        return "orders/orderForm";
    }

    @PostMapping("/orderForm/{carId}")
    public String save(@PathVariable int carId, @ModelAttribute(ORDER_MODEL_ATTRIBUTE) @Valid Order order,
                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ORDER_MODEL_ATTRIBUTE, order);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "orders/orderForm";
        }

        orderService.save(order, carId);
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

    @GetMapping("/orders/deleteMyOrder/{id}")
    public String delete(@PathVariable int id) {
        orderService.delete(id);
        return REDIRECT_PREFIX + "/orders/viewMyOrders";
    }

    @GetMapping("/approve/{id}")
    public String orderApproved(@PathVariable int id) {
        orderService.approve(id);
        return REDIRECT_PREFIX + "/orders/viewOrders";
    }

    @GetMapping("/reject/{id}")
    public String orderReject(@PathVariable int id) {
        orderService.reject(id);
        return REDIRECT_PREFIX + "/orders/viewOrders";
    }

    @GetMapping("/order/pay/{id}")
    public String payOrder(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        order.getCar().setAvailable(false); // update available status in database
        model.addAttribute("order", order);
        return "orders/receiptOfPayment";
    }


}
