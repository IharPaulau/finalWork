package service.impl;

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
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int delete(int id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> getOwnOrders() {
        return orderDao.getOwnOrders();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
