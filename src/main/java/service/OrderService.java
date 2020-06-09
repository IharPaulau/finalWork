package service;

import models.Order;

import java.util.List;

public interface OrderService {

    int save(Order order);

    Order getOrderById(int orderId);

    void delete(int orderId);

    List<Order> getOwnOrders(String username);

    List<Order> getOrders();

    int reject(int id);

    int approve(int id);

    void setOrderStatusToPaid(Order order);

    void cancelExpiredOrders();

    void autoChangeOrderStatusToReturn();

    int repairInvoice(int id);

    int complete(int id);

    int update(Order order);
}

