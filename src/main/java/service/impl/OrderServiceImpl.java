package service.impl;

import beans.Car;
import beans.Order;
import beans.OrderStatus;
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

import static utils.DateConverter.dateToString;

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
        order.setOrderStatus(OrderStatus.NOT_VERIFIED);
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
        Order order = getOrderById(id);
        carService.setCarAvailable(order.getCar());
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
        order.setPayTillDate(setterPaymentDeadline());
        orderDao.setDeadline(order, dateToString(order.getPayTillDate()));
        return orderDao.approve(id);
    }


    private Date setterPaymentDeadline() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 1);
        return cal.getTime();

    }

    @Override
    public void setOrderStatusToPaid(Order order) {
        orderDao.setOrderStatusToPaid(order);
    }

    @Override
    public void cancelExpiredOrders() {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            Calendar presentTime = Calendar.getInstance();
            if (order.getPayTillDate() != null)
                if (order.getPayTillDate().before(presentTime.getTime()) & (order.getOrderStatus() == OrderStatus.APPROVED)) {
                    reject(order.getId());
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
