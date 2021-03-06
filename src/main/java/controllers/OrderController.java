package controllers;

import forms.RejectReasonForm;
import models.Order;
import enums.OrderStatus;
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
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.*;

@Controller
public class OrderController {

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders/orderForm/{carId}")
    public String showForm(@PathVariable int carId, Model model) {
        model.addAttribute(ORDER_MODEL_ATTRIBUTE, new Order());
        model.addAttribute("carId", carId);
        return "orders/orderForm";
    }

    @PostMapping("/orders/orderForm/{carId}")
    public String save(@ModelAttribute(ORDER_MODEL_ATTRIBUTE) @Valid Order order,
                       BindingResult bindingResult, Model model, @PathVariable int carId) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ORDER_MODEL_ATTRIBUTE, order);
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("carId", carId);
            return "orders/orderForm";
        }

        orderService.createOrder(order);
        return REDIRECT_PREFIX + "/orders/viewMyOrders";
    }


    @GetMapping("/orders/viewMyOrders")
    public String viewMyOrders(Model model, Principal principal) {
        String username = principal.getName();
        List<Order> list = orderService.getOwnOrders(username);
        model.addAttribute("list", list);
        return "orders/viewMyOrders";
    }

    @GetMapping("/orders/viewOrders")
    public String viewAllOrders(Model model) {
        List<Order> list = orderService.getOrders();
        long returnOrders = list.stream().filter(x -> OrderStatus.RETURN.equals(x.getOrderStatus())).count();
        model.addAttribute("list", list);
        model.addAttribute("returnOrders", returnOrders);
        return "orders/viewOrders";
    }

    @GetMapping("/orders/deleteMyOrder/{id}")
    public String delete(@PathVariable int id) {
        orderService.delete(id);
        return REDIRECT_PREFIX + "/orders/viewMyOrders";
    }

    @GetMapping("/approve/{id}")
    public String orderApproved(@PathVariable int id) {
        orderService.approveOrder(id);
        return REDIRECT_PREFIX + "/orders/viewOrders";
    }

    @GetMapping("/rejectReason/{orderId}")
    public String reasonForm(@PathVariable int orderId, Model model) {

        model.addAttribute("rejectReasonForm", new RejectReasonForm(orderId));
        return "orders/rejectReason";
    }

    @PostMapping("/rejectReason/{orderId}")
    public String orderReject(@PathVariable int orderId, RejectReasonForm rejectReasonForm) {
        Order order = orderService.getOrderById(orderId);
        order.setRejectReason(rejectReasonForm.getRejectReason());
        orderService.rejectOrder(order);
        return REDIRECT_PREFIX + "/orders/viewOrders";
    }

    @GetMapping("/order/payOrder/{id}")
    public String payOrder(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        orderService.setOrderStatusToPaid(order);
        carService.setCarNoMoreAvailable(order.getCar());
        model.addAttribute("kindOfReceipt", "car.rent.receipt");
        return "orders/receiptOfPayment";
    }

    @GetMapping("/order/payRepair/{id}")
    public String payRepair(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("kindOfReceipt", "car.repair.receipt");
        orderService.completeOrder(order.getId());
              return "orders/receiptOfPayment";
    }


    @GetMapping("/orders/completeOrders")
    public String showCompletedOrders(Model model) {
        List<Order> allOrders = orderService.getOrders();
        List<Order> returnOrders = allOrders.stream().filter(x -> OrderStatus.RETURN.equals(x.getOrderStatus())).collect(Collectors.toList());
        model.addAttribute("list", returnOrders);
        return "orders/completeOrders";
    }

    @GetMapping("/returnCar/{id}")
    public String orderCompleteAndReturnCar(@PathVariable int id) {
        orderService.completeOrder(id);
        return REDIRECT_PREFIX + "/orders/completeOrders";
    }

    @GetMapping("/repairInvoice/{id}")
    public String makeInvoiceForRepairCar(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute(ORDER_MODEL_ATTRIBUTE, order);
        return "orders/repairInvoice";
    }

    @PostMapping("/repairInvoice/{id}")
    public String invoiceForm(Order order, Model model) {

        if (ZERO_COMPENSATION_AMOUNT.equals(order.getCompensationAmount())) {
            model.addAttribute(ORDER_MODEL_ATTRIBUTE, order);
            model.addAttribute("compensationError", "compensation.amount.validation");

            return "orders/repairInvoice";
        }
        orderService.repairInvoice(order.getId()); //t odo work ?
        orderService.updateCompensationAmount(order);
        return REDIRECT_PREFIX + "/orders/viewOrders";
    }


}
