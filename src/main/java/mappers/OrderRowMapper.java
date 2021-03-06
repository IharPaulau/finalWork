package mappers;


import models.Car;
import models.Order;
import enums.OrderStatus;
import models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static utils.DateConverter.stringToDate;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setPassportSeries(rs.getString("passportSeries"));
        order.setPassportNumber(rs.getInt("passportNumber"));
        order.setPassportId(rs.getString("passportId"));
        order.setOrderStatus(OrderStatus.valueOf(rs.getString("orderStatus")));
        order.setRentalPeriodInDays(rs.getInt("rentalPeriodInDays"));
        order.setCar(fillCar(rs));
        order.setUser(fillUser(rs));
        order.setCompensationAmount(rs.getInt("compensationAmount"));
        String payDay = rs.getString("payTillDate");
        String startRent = rs.getString("rentalStartTime");
        String endRent = rs.getString("rentalEndTime");
        try {
            order.setPayTillDate(stringToDate(payDay));
            order.setRentalStartTime(stringToDate(startRent));
            order.setRentalEndTime(stringToDate(endRent));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setRejectReason(rs.getString("rejectReason"));
        return order;
    }

    private User fillUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("userId"));
        user.setUsername(rs.getString("username"));
        return user;
    }

    private Car fillCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("carId"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setTypeBody(rs.getString("typeBody"));
        car.setTypeEngine(rs.getString("typeEngine"));
        car.setBodyColor(rs.getString("bodyColor"));
        car.setCostPerOneDay(rs.getInt("costPerOneDay"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvailable(rs.getBoolean("available"));
        return car;

    }
}
