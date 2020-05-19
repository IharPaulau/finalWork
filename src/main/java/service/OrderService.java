package service;


import beans.Order;

import java.util.List;

public interface OrderService {

    int save(Order order);

    List<Order> getOrders();

    Order getOrderById(int id);

    int update(Order car);

    int delete(int id);

    List<Order> getOwnOrders();

}
