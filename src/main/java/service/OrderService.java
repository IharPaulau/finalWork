package service;


import beans.Car;
import beans.Order;


import java.util.List;

public interface OrderService {

    int save(Order order, int carId);

    Order getOrderById(int id);

    int delete(int id);

    List<Order> getOwnOrders();

    List<Order> getOrders();

    int reject(int id);

    int approve(int id);

    List<Car> getCars();
}

