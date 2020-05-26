package service.impl;

import beans.Car;
import beans.Order;
import dao.CarDao;
import dao.OrderDao;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private CarDao carDao;


    @Override
    public int save(Order order) {
        return orderDao.save(order);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = orderDao.getOrders();
        for(Order order:orders){
            order.setCar(carDao.getCarById(order.getCar().getId()));
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderDao.getOrderById(id);
        order.setCar(carDao.getCarById(order.getCar().getId()));
        return order;
    }

    @Override
    public int delete(int id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> getOwnOrders() {
        List<Order> orders = orderDao.getOwnOrders();
        for(Order order:orders){
            order.setCar(carDao.getCarById(order.getCar().getId()));
        }
        return orders;
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

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
}
