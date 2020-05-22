package mappers;


import beans.Car;
import beans.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();
        Car car = new Car();
        car.setId(rs.getInt("carId"));
        order.setId(rs.getInt("id"));
        order.setPassportSeries(rs.getString("passportSeries"));
        order.setPassportNumber(rs.getInt("passportNumber"));
        order.setPassportId(rs.getString("passportId"));
        Object checkForNull = rs.getObject("orderApproved");
        if (checkForNull != null) {
            order.setOrderApproved(rs.getBoolean("orderApproved"));
        }
        order.setRentalPeriodInDays(rs.getInt("rentalPeriodInDays"));
        order.setCar(car);
        return order;
    }
}
