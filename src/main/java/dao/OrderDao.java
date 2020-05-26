package dao;


import beans.Car;
import beans.Order;


import java.util.List;

public interface OrderDao {

    int save(Order order);

    int delete(int id);

    Order getOrderById(int id);

    List<Order> getOwnOrders(int id);

    List<Order> getOrders();

    int reject(int id);

    int approve(int id);

//    List<Car> getCars();

}
