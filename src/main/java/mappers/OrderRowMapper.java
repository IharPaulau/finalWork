package mappers;


import beans.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("userId"));
        order.setCarId(rs.getInt("carId"));
        order.setPassportSeries(rs.getString("passportSeries"));
        order.setPassportNumber(rs.getInt("passportNumber"));
        order.setPassportId(rs.getString("passportId"));
        Object checkForNull = rs.getObject("orderApproved");
        if (checkForNull != null) {
            order.setOrderApproved(rs.getBoolean("orderApproved"));
        }
        order.setRentalPeriodInDays(rs.getInt("rentalPeriodInDays"));
        return order;
    }
}
