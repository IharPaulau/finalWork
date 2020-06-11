package dao;


import models.Order;


import java.util.List;

public interface OrderDao {

    /**
     * Save new or update current order
     *
     * @param order order to save in db or update
     * @return orderId new order or updated order
     */
    int save(Order order);

    /**
     * Set in current order value of compensationAmount
     *
     * @param order order to change value
     * @return
     */
    int updateCompensationAmount(Order order);

    /**
     * Delete from db current order
     *
     * @param orderId orderId to delete
     * @return
     */
    int delete(int orderId);

    /**
     * Search order by current
     *
     * @param orderId orderId to search
     * @return Order
     */
    Order getOrderById(int orderId);

    /**
     * Search all orders for current User
     *
     * @param userId userId to search all orders
     * @return list of orders
     */
    List<Order> getOwnOrders(int userId);

    /**
     * Search all orders
     *
     * @return list of all orders
     */
    List<Order> getOrders();

    /**
     * Set payTillDate id current order
     *
     * @param order order to update in database
     * @param payTillDay payTillDay converted Date in String
     */
    void setDeadline(Order order, String payTillDay);

    /**
     * Set value of attributes startRent and endRent
     * in current Order
     *
     * @param order order to update
     * @param startRent startRent converted Date in String
     * @param endRent endRent converted Date in String
     */
    void setTime(Order order, String startRent, String endRent);
}
