package dao.impl;


import models.Order;
import dao.OrderDao;
import mappers.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String ADD_NEW_ORDER = "INSERT INTO orders(userId, carId, passportSeries, passportNumber, passportId, rentalPeriodInDays, payTillDate, orderStatus, compensationAmount) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_COMPENSATION_AMOUNT = "UPDATE orders SET compensationAmount=? WHERE id=?";
    private static final String CHANGE_ORDER_STATUS = "UPDATE orders SET orderStatus=? WHERE id=?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id WHERE o.id=?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id";
    private static final String SELECT_ALL_OWN_ORDERS = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id WHERE userId=?";
    private static final String SET_DEADLINE_TO_PAID = "UPDATE orders SET payTillDate=? WHERE id=?";
    private static final String SET_START_AND_END_OF_RENT = "UPDATE orders SET rentalStartTime=?, rentalEndTime=? WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Order order) {
        return jdbcTemplate.update(ADD_NEW_ORDER, order.getUser().getId(), order.getCar().getId(), order.getPassportSeries(), order.getPassportNumber(),
                order.getPassportId(), order.getRentalPeriodInDays(), order.getPayTillDate(), order.getOrderStatus().getName(), order.getCompensationAmount());
    }

    @Override
    public int updateCompensationAmount(Order order) {
        return jdbcTemplate.update(UPDATE_COMPENSATION_AMOUNT, order.getCompensationAmount(), order.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE_ORDER, id);
    }

    @Override
    public Order getOrderById(int id) {
        return jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID, new Object[]{id}, new OrderRowMapper());
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(SELECT_ALL_ORDERS, new OrderRowMapper());
    }

    @Override
    public List<Order> getOwnOrders(int id) {
        return jdbcTemplate.query(SELECT_ALL_OWN_ORDERS, new OrderRowMapper(), id);
    }

    @Override
    public int changeOrderStatus(Order order) {
        return jdbcTemplate.update(CHANGE_ORDER_STATUS, order.getOrderStatus().getName(), order.getId());
    }

    @Override
    public void setDeadline(Order order, String payTillDay) {
        jdbcTemplate.update(SET_DEADLINE_TO_PAID, payTillDay, order.getId());
    }

    @Override
    public void setTimes(Order order, String startRent, String endRent) {
        jdbcTemplate.update(SET_START_AND_END_OF_RENT, startRent, endRent, order.getId());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
