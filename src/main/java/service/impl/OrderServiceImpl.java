package service.impl;

import beans.Car;
import beans.Order;
import beans.User;
import dao.CarDao;
import dao.OrderDao;
import service.OrderService;
import service.UserService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private UserService userService;

    @Override
    public int save(Order order) {
        order.setUser(userService.getUserByName(order.getUser().getUsername()));
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
        return orderDao.reject(id);
    }

    @Override
    public int approve(int id) {
        return orderDao.approve(id);
    }

//    @Override
//    public List<Car> getCars() {
//        return orderDao.getCars();
//    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

        public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
