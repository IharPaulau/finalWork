package service.impl;

import beans.Car;
import beans.Order;
import dao.OrderDao;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Override
    public int save(Order order) {
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
    public List<Order> getOwnOrders() {
        return orderDao.getOwnOrders();
    }

    @Override
    public int reject(int id) {
        return orderDao.reject(id);
    }

    @Override
    public int approve(int id) {
        return orderDao.approve(id);
    }

    @Override
    public List<Car> getCars() {
        return orderDao.getCars();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
