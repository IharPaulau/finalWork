package service.impl;

import models.Car;
import models.Order;
import enums.OrderStatus;
import models.User;
import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import service.CarService;
import service.OrderService;
import service.UserService;
import utils.StateChangerRunnable;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import static utils.DateConverter.dateToString;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;
    private UserService userService;
    private CarService carService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public int createOrder(Order order) {
        User user = userService.getUserByName(order.getUser().getUsername());
        order.setUser(user);
        int carId = order.getCar().getId();
        Car car = carService.getCarById(carId);
        carService.setCarNoMoreAvailable(car);
        return orderDao.save(order);
    }

    @Override
    public int save(Order order) {
        int orderId = orderDao.save(order);
        LOGGER.info(String.format("Order saved with id: %s", orderId));
        return orderId;
    }

    @Override
    public void updateCompensationAmount(Order order) {
        orderDao.updateCompensationAmount(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public Order getOrderById(int id) {
        try {
            return orderDao.getOrderById(id);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.warn(String.format("Could not find order with id %s", id));
            return null;
        }

    }

    @Override
    public void delete(int id) {
        Order order = getOrderById(id);
        if (order != null) {
            carService.setCarAvailable(order.getCar());
            orderDao.delete(id);
        } else {
            LOGGER.warn(String.format("Could not delete order with id %s. Order was not found", id));
        }
    }

    @Override
    public List<Order> getOwnOrders(String userName) {
        User user = userService.getUserByName(userName);
        if (user != null) {
            int id = userService.getUserByName(userName).getId();
            return orderDao.getOwnOrders(id);
        } else {
            LOGGER.warn(String.format("Could not find orders for user with name %s. User was not found", userName));
            return Collections.emptyList();
        }
    }

    @Override
    public void rejectOrder(Order order) {
        carService.setCarAvailable(order.getCar());
        order.setOrderStatus(OrderStatus.REJECTED);
        save(order);
        LOGGER.info(String.format("Order with id: %s rejected", order.getId()));
    }

    @Override
    public void approveOrder(int orderId) {
        Order order = orderDao.getOrderById(orderId);
        order.setPayTillDate(setterPaymentDeadline(1));
        order.setOrderStatus(OrderStatus.APPROVED);
        carService.setCarNoMoreAvailable(order.getCar());
        orderDao.setDeadline(order, dateToString(order.getPayTillDate()));
        save(order);
        LOGGER.info(String.format("Order with id: %s approved", order.getId()));
    }

    @Override
    public void completeOrder(int id) {
        Order order = orderDao.getOrderById(id);
        order.setOrderStatus(OrderStatus.COMPLETED);
        carService.setCarAvailable(order.getCar());
        save(order);
        LOGGER.info(String.format("Order with id: %s completed", order.getId()));
    }

    @Override
    public void repairInvoice(int id) {
        Order order = orderDao.getOrderById(id);
        order.setOrderStatus(OrderStatus.RECOVERY);
        save(order);
        LOGGER.info(String.format("Order with id: %s set status to recovery", order.getId()));
    }

    private Date setterPaymentDeadline(int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    private Date setterRentalEnd(Date date, Order order) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(order.getRentalStartTime());
        cal.add(Calendar.MINUTE, order.getRentalPeriodInDays());
        return cal.getTime();
    }

    @Override
    public void setOrderStatusToPaid(Order order) {
        order.setRentalStartTime(new Date());
        order.setRentalEndTime(setterRentalEnd(new Date(), order));
        orderDao.setTime(order, dateToString(order.getRentalStartTime()), dateToString(order.getRentalEndTime()));
        order.setOrderStatus(OrderStatus.IN_RENT);
        save(order);
    }

    @Override
    public void cancelExpiredOrders() {
        List<Order> orders = getOrders();
        Calendar presentTime = Calendar.getInstance();
        for (Order order : orders) {
            if (OrderStatus.APPROVED.equals(order.getOrderStatus()) && order.getPayTillDate().before(presentTime.getTime())) {
                rejectOrder(order);
            }
        }
    }

    @Override
    public void autoChangeOrderStatusToReturn() {
        List<Order> orders = getOrders();
        Calendar presentTime = Calendar.getInstance();
        for (Order order : orders) {
            if (OrderStatus.IN_RENT.equals(order.getOrderStatus()) && order.getRentalEndTime().before(presentTime.getTime())) {
                order.setOrderStatus(OrderStatus.RETURN);
                save(order);
            }
        }
    }

    @PostConstruct
    private void runCancellationOrdersThread() {
        Runnable runnable = new StateChangerRunnable();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(runnable);
        Thread stateChanger = new Thread(runnable, "stateChanger");
        stateChanger.start();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
