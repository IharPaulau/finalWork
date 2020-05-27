package dao.impl;

import beans.Car;
import beans.Order;
import dao.OrderDao;
import mappers.CarRowMapper;
import mappers.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String ADD_NEW_ORDER = "INSERT INTO orders(userId, carId, passportSeries, passportNumber, passportId, rentalPeriodInDays) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_APPROVED_STATUS = "UPDATE orders SET orderApproved=? WHERE id=?";
    private static final String CHANGE_PAID_STATUS = "UPDATE orders SET orderPaid=? WHERE id=?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id WHERE o.id=?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id";
    private static final String SELECT_ALL_OWN_ORDERS = "SELECT * FROM orders AS o JOIN cars AS c ON o.carId = c.id JOIN users AS u ON o.userId = u.id WHERE userId=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Order order) {
        return jdbcTemplate.update(ADD_NEW_ORDER, order.getUser().getId(), order.getCar().getId(), order.getPassportSeries(), order.getPassportNumber(),
                order.getPassportId(), order.getRentalPeriodInDays());
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
    public int reject(int id) {
        return jdbcTemplate.update(CHANGE_APPROVED_STATUS, false, id);
    }

    @Override
    public int approve(int id) {
        return jdbcTemplate.update(CHANGE_APPROVED_STATUS, true, id);
    }

    @Override
    public void setOrderStatusToPaid(Order order) {
        jdbcTemplate.update(CHANGE_PAID_STATUS, true, order.getId());
    }

//    @Override
//    public List<Car> getCars() {
//        return jdbcTemplate.query(SELECT_ALL_CARS, new CarRowMapper());
//    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
