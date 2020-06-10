package dao;


import models.Order;


import java.util.List;

public interface OrderDao {

    int save(Order order);

    int updateCompensationAmount(Order order);

    int delete(int id);

    Order getOrderById(int id);

    List<Order> getOwnOrders(int id);

    List<Order> getOrders();

    int changeOrderStatus(Order order);

    void setDeadline(Order order, String payTillDay);

    void setTime(Order order, String startRent, String endRent);
}
