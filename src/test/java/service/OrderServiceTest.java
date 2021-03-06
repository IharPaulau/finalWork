package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import enums.OrderStatus;
import models.Car;
import models.Order;
import models.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/test_application-context.xml")
public class OrderServiceTest extends AbstractServiceTest {

    private static final String TEST_PASSPORT_SERIES = "KH";
    private static final String TEST_PASSPORT_ID = "testPassportId";
    private static final Integer TEST_PASSPORT_NUMBER = 1000000;
    private static final Integer TEST_RENTAL_PERIOD_IN_DAYS = 3;
    private static final Integer TEST_COMPENSATION_AMOUNT = 100;

    private int testOrderId;
    private int testCarId;
    private User testUser;

    @Autowired
    private OrderService orderService;

    @Before
    public void init() {
        testUser = saveTestUser();
        testCarId = saveTestCar();
        testOrderId = orderService.createOrder(createTestOrder(testUser));
    }

    @Test
    public void test_saveOrder() {
        Order notExistingOrder = orderService.getOrderById(testOrderId + 1);
        assertNull(notExistingOrder);
        Order orderToSave = createTestOrder(testUser);
        int orderId = orderService.createOrder(orderToSave);
        Order savedOrder = orderService.getOrderById(orderId);
        assertNotNull(savedOrder);
    }

    @Test
    public void test_getOrderById() {
        Order order = orderService.getOrderById(testOrderId);
        assertEquals(TEST_PASSPORT_SERIES, order.getPassportSeries());
        assertEquals(TEST_PASSPORT_NUMBER, order.getPassportNumber());
        assertEquals(TEST_PASSPORT_ID, order.getPassportId());
        assertEquals(TEST_RENTAL_PERIOD_IN_DAYS, order.getRentalPeriodInDays());
        assertEquals(OrderStatus.NOT_VERIFIED, order.getOrderStatus());
        assertNull(order.getPayTillDate());
        assertNull(order.getRentalStartTime());
        assertNull(order.getRentalEndTime());

        User orderUser = order.getUser();
        assertNotNull(orderUser);
        assertEquals(testUser.getId(), orderUser.getId());
        assertEquals(TEST_USERNAME, orderUser.getUsername());

        Car orderCar = order.getCar();
        assertNotNull(orderCar);
        assertEquals(testCarId, orderCar.getId());
        assertEquals(TEST_CAR_BRAND, orderCar.getBrand());
        assertEquals(TEST_CAR_MODEL, orderCar.getModel());
        assertEquals(TEST_CAR_TYPE_BODY, orderCar.getTypeBody());
        assertEquals(TEST_CAR_TYPE_ENGINE, orderCar.getTypeEngine());
        assertEquals(TEST_CAR_BODY_COLOR, orderCar.getBodyColor());
        assertEquals(TEST_CAR_COST, orderCar.getCostPerOneDay());
        assertEquals(TEST_CAR_TRANSMISSION, orderCar.getTransmission());
        assertFalse(orderCar.isAvailable());
    }

    @Test
    public void test_delete() {
        orderService.delete(testOrderId);
        Order order = orderService.getOrderById(testOrderId);
        assertNull(order);
    }

    @Test
    public void test_getOwnOrders() {
        User anotherUser = createTestUser(TEST_USERNAME_2, TEST_USER_EMAIL_2);
        userService.saveUserWithRole(anotherUser);
        orderService.createOrder(createTestOrder(anotherUser));
        List<Order> orders = orderService.getOwnOrders(TEST_USERNAME);
        assertEquals(1, orders.size());
    }

    @Test
    public void test_getOwnOrders_returnEmptyListWhenUserNotExist() {
        List<Order> orders = orderService.getOwnOrders(TEST_NOT_EXISTING_USERNAME);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void test_getOrders() {
        User anotherUser = createTestUser(TEST_USERNAME_2, TEST_USER_EMAIL_2);
        userService.saveUserWithRole(anotherUser);
        orderService.createOrder(createTestOrder(anotherUser));
        List<Order> orders = orderService.getOrders();
        assertEquals(2, orders.size());
    }

    @Test
    public void test_rejectOrder() {
        Order order = orderService.getOrderById(testOrderId);
        orderService.rejectOrder(order);
        Order rejectedOrder = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.REJECTED, rejectedOrder.getOrderStatus());
        Car car = carService.getCarById(rejectedOrder.getCar().getId());
        assertTrue(car.isAvailable());
    }

    @Test
    public void test_approveOrder() {
        orderService.approveOrder(testOrderId);
        Order order = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.APPROVED, order.getOrderStatus());
        assertNotNull(order.getPayTillDate());
        Car car = carService.getCarById(order.getCar().getId());
        assertFalse(car.isAvailable());
    }

    @Test
    public void test_setOrderStatusToPaid() {
        Order order = orderService.getOrderById(testOrderId);
        assertNull(order.getRentalStartTime());
        assertNull(order.getRentalEndTime());
        assertEquals(OrderStatus.NOT_VERIFIED, order.getOrderStatus());
        orderService.setOrderStatusToPaid(order);
        Order updatedOrder = orderService.getOrderById(testOrderId);
        assertNotNull(updatedOrder.getRentalStartTime());
        assertNotNull(updatedOrder.getRentalEndTime());
        assertEquals(OrderStatus.IN_RENT, updatedOrder.getOrderStatus());
    }

    @Test
    public void test_cancelExpiredOrders() {
        orderService.approveOrder(testOrderId);
        Order order = orderService.getOrderById(testOrderId);
        order.setPayTillDate(setterPaymentDeadline(-2));
        orderService.save(order);
        orderService.cancelExpiredOrders();
        Order updatedOrder = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.REJECTED, updatedOrder.getOrderStatus());
        assertNotNull(updatedOrder.getPayTillDate());
        Car car = carService.getCarById(updatedOrder.getCar().getId());
        assertTrue(car.isAvailable());
    }

    @Test
    public void test_autoChangeOrderStatusToReturn() {
        Order order = orderService.getOrderById(testOrderId);
        orderService.setOrderStatusToPaid(order);
        orderService.autoChangeOrderStatusToReturn();
        Order updatedOrder = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.RETURN, updatedOrder.getOrderStatus());
    }

    @Test
    public void test_repairInvoice() {
        orderService.repairInvoice(testOrderId);
        Order order = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.RECOVERY, order.getOrderStatus());
    }

    @Test
    public void test_completeOrder() {
        orderService.completeOrder(testOrderId);
        Order order = orderService.getOrderById(testOrderId);
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
        Car car = carService.getCarById(order.getCar().getId());
        assertTrue(car.isAvailable());
    }

    @Test
    public void test_updateCompensationAmount() {
        Order order = orderService.getOrderById(testOrderId);
        order.setCompensationAmount(TEST_COMPENSATION_AMOUNT);
        orderService.updateCompensationAmount(order);
        Order updatedOrder = orderService.getOrderById(testOrderId);
        assertEquals(TEST_COMPENSATION_AMOUNT, updatedOrder.getCompensationAmount());
    }


    private Order createTestOrder(User user) {
        Order order = new Order();
        Car car = new Car();
        car.setId(testCarId);
        order.setUser(user);
        order.setCar(car);
        order.setPassportSeries(TEST_PASSPORT_SERIES);
        order.setPassportNumber(TEST_PASSPORT_NUMBER);
        order.setPassportId(TEST_PASSPORT_ID);
        order.setRentalPeriodInDays(TEST_RENTAL_PERIOD_IN_DAYS);
        return order;
    }

    private Date setterPaymentDeadline(int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
}