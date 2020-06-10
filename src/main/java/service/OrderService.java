package service;

import models.Order;

import java.util.List;

/**
 * Service to manage {@link Order} objects.
 */
public interface OrderService {

    /**
     * Create new Order
     *
     * @param order order to save
     * @return id of new created order
     */

    int createOrder(Order order);

    /**
     * Persist order to db
     *
     * @param order order to persist
     * @return id of updated order
     */
    int save(Order order);

    /**
     * Get order by current orderId
     *
     * @param orderId orderId to search
     * @return Order
     */
    Order getOrderById(int orderId);

    /**
     * Delete order by current orderId
     *
     * @param orderId orderId to search
     */
    void delete(int orderId);

    /**
     * Search User by current userName and
     * get all orders for this User
     *
     * @param userName userName to search orders
     * @return list of orders
     */
    List<Order> getOwnOrders(String userName);

    /**
     * Get all orders
     *
     * @return list of all orders
     */
    List<Order> getOrders();

    /**
     * Get order by current orderId,
     * change order status to reject
     * and change car status to available
     *
     * @param orderId orderId to change
     */
    void rejectOrder(int orderId);

    /**
     * Get order by current orderId,
     * change order status to approved
     * and change car status to not available
     *
     * @param orderId orderId to change
     */
    void approveOrder(int orderId);

    /**
     * Set rental Start and End time to current order,
     * change order status to in_rent and save this order
     *
     * @param order order
     */
    void setOrderStatusToPaid(Order order);

    /**
     * Reject approved orders if paytilldate was missed
     */
    void cancelExpiredOrders();

    /**
     * Change order status to return for in rent orders
     * when rentalendtime has come
     */
    void autoChangeOrderStatusToReturn();

    /**
     * Change order status to recovery by current orderId
     *
     * @param orderId orderId to change
     */
    void repairInvoice(int orderId);

    /**
     * Change order status to complete by current orderId
     *
     * @param orderId orderId to change
     */
    void completeOrder(int orderId);

    /**
     * Update compensation amount for current order
     *
     * @param order order to change
     */
    void updateCompensationAmount(Order order);
}

