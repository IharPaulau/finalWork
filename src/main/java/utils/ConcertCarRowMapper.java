package utils;

import beans.Car;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcertCarRowMapper<C> implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car concertCar = new Car();
        concertCar.setId(rs.getInt("id"));
        concertCar.setBrand(rs.getString("brand"));
        concertCar.setModel(rs.getString("model"));
        concertCar.setFuelTank(rs.getInt("fuelTank"));
        return concertCar;
    }
}