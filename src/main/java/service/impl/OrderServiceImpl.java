package service.impl;

import beans.Car;
import beans.Order;
import beans.User;
import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import service.CarService;
import service.OrderService;
import service.UserService;
import utils.OrderCancellationRunnable;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private UserService userService;
    private CarService carService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public int save(Order order) {
        String usernameFromJSP = order.getUser().getUsername();
        User user = userService.getUserByName(usernameFromJSP);
        order.setUser(user);
        int carId = order.getCar().getId();
        Car car = carService.getCarById(carId);
        carService.setCarNoMoreAvailable(car);
        return orderDao.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public int delete(int id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> getOwnOrders(String name) {
        int id = userService.getUserByName(name).getId();
        return orderDao.getOwnOrders(id);
    }

    @Override
    public int reject(int id) {
        Order order = orderDao.getOrderById(id);
        carService.setCarAvailable(order.getCar());
        return orderDao.reject(id);
    }

    @Override
    public int approve(int id) {
        Order order = orderDao.getOrderById(id);
        order.setPayTillDate(new Date().toInstant().);
        return orderDao.approve(id);
    }

    @Override
    public void setOrderStatusToPaid(Order order) {
        orderDao.setOrderStatusToPaid(order);
    }

    @Override
    public void cancelExpiredOrders() {
        //TODO logic to get all order for date
        List<Order> orders = orderDao.getOrders();
        for (Order order : orders) {
            Calendar payTillDate = Calendar.getInstance();
            if (payTillDate.after(Calendar.getInstance())) {
                order.setStatus(OrderStatus.CANCEL);
                orderDao.save(order);
                Car car = order.getCar();
                car.setAvailable(true);
                carService.save(car);
            }
        }
    }

    @PostConstruct
    private void runCancellationOrdersThread() {
        Runnable runnable = new OrderCancellationRunnable();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(runnable);
        Thread orderCancellationThread = new Thread(runnable, "OrderCancellationThread");
        orderCancellationThread.start();
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
