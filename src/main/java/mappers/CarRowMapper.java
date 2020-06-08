package mappers;

import models.Car;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
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