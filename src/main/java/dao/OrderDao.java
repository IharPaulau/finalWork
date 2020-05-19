package dao;



import beans.Order;

import java.util.List;

public interface OrderDao {

    int save(Order order);

    int update(Order order);

    int delete(int id);

    Order getOrderById(int id);

    List<Order> getOrders();

    List<Order> getOwnOrders();
}
