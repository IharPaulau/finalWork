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
    public int update(Order order){
        return orderDao.update(order);
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
        order.setOrderStatus(OrderStatus.REJECTED);
        return orderDao.changeOrderStatus(order);
    }

    @Override
    public int approve(int id) {
        Order order = orderDao.getOrderById(id);
        order.setPayTillDate(setterPaymentDeadline());
        order.setOrderStatus(OrderStatus.APPROVED);
        carService.setCarNoMoreAvailable(order.getCar());
        orderDao.setDeadline(order, dateToString(order.getPayTillDate()));
        return orderDao.changeOrderStatus(order);
    }

    @Override
    public int complete(int id){
        Order order = orderDao.getOrderById(id);
        order.setOrderStatus(OrderStatus.COMPLETED);
        carService.setCarAvailable(order.getCar());
        return orderDao.changeOrderStatus(order);
    }

    @Override
    public int repairInvoice(int id){
        Order order = orderDao.getOrderById(id);
        order.setOrderStatus(OrderStatus.RECOVERY); // поменять смену статуса после установки счета за ремонт.
        return orderDao.changeOrderStatus(order);
    }

    private Date setterPaymentDeadline() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 1);
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
        orderDao.setTimes(order, dateToString(order.getRentalStartTime()), dateToString(order.getRentalEndTime()));
        order.setOrderStatus(OrderStatus.IN_RENT);
        orderDao.changeOrderStatus(order);
    }

    @Override
    public void cancelExpiredOrders() {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            autoReturnCars(order);
            Calendar presentTime = Calendar.getInstance();
            if (order.getOrderStatus() == OrderStatus.APPROVED)
                if (order.getPayTillDate().before(presentTime.getTime())) {
                    reject(order.getId());
                }
        }
    }

    @Override
    public void autoReturnCars(Order order) {
        if (order.getOrderStatus() == OrderStatus.IN_RENT) {
            Calendar presentTime = Calendar.getInstance();
            if (order.getRentalEndTime().before(presentTime.getTime())) {
                order.setOrderStatus(OrderStatus.RETURN);
                orderDao.changeOrderStatus(order);

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
