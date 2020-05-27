package service;


import beans.Car;
import beans.Order;
import beans.User;
import org.springframework.ui.Model;


import java.util.List;

public interface OrderService {

    int save(Order order);

    Order getOrderById(int id);

    int delete(int id);

    List<Order> getOwnOrders(String user);

    List<Order> getOrders();

    int reject(int id);

    int approve(int id);

    void setOrderStatusToPaid(Order order);

    void cancelExpiredOrders();
}

