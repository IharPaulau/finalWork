package dao.impl;

import models.Car;
import dao.CarDao;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;

import mappers.CarRowMapper;


import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String ADD_NEW_CAR = "INSERT INTO cars(brand,model,typeBody,typeEngine,bodyColor," +
            "costPerOneDay,transmission, available) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_CAR = "UPDATE cars SET brand=?,model=?,typeBody=?,typeEngine=?,bodyColor=?," +
            "costPerOneDay=?,transmission=?,available=? WHERE id=?";
    private static final String DELETE_CAR = "DELETE FROM cars WHERE id=?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM cars";
    private static final String UPDATE_CAR_AVAILABILITY = "UPDATE cars SET available=? WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Car car) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return preparedStatement(car, keyHolder);
    }

    @Override
    public int update(Car car) {
        return jdbcTemplate.update(UPDATE_CAR, car.getBrand(), car.getModel(), car.getTypeBody(), car.getTypeEngine(),
                car.getBodyColor(), car.getCostPerOneDay(), car.getTransmission(), true, car.getId());
    }

    @Override
    public int delete(int carId) {
        return jdbcTemplate.update(DELETE_CAR, carId);
    }

    @Override
    public Car getCarById(int id) {
        return jdbcTemplate.queryForObject(SELECT_CAR_BY_ID, new Object[]{id}, new CarRowMapper());
    }

    @Override
    public List<Car> getCars() {
        return jdbcTemplate.query(SELECT_ALL_CARS, new CarRowMapper());
    }

    @Override
    public int updateCarAvailability(int carId, boolean available) {
        return jdbcTemplate.update(UPDATE_CAR_AVAILABILITY, available, carId);
    }


    private int preparedStatement(Car car, KeyHolder keyHolder) {
        jdbcTemplate.update(getPreparedStatementCreator(car), keyHolder);
        return keyHolder.getKey().intValue();
    }

    private PreparedStatementCreator getPreparedStatementCreator(Car car) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_NEW_CAR, new String[]{"id"});
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getTypeBody());
            ps.setString(4, car.getTypeEngine());
            ps.setString(5, car.getBodyColor());
            ps.setInt(6, car.getCostPerOneDay());
            ps.setString(7, car.getTransmission());
            ps.setBoolean(8, car.isAvailable());
            return ps;
        };
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}