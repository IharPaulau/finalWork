package dao.impl;

import beans.Car;
import dao.CarDao;

import org.springframework.jdbc.core.JdbcTemplate;

import mappers.CarRowMapper;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String ADD_NEW_CAR = "INSERT INTO cars(brand,model,costPerOneDay) VALUES(?, ?, ?)";
    private static final String UPDATE_CAR = "UPDATE cars SET brand=?, model=?, costPerOneDay=? WHERE id=?";
    private static final String DELETE_CAR = "DELETE FROM cars WHERE id=?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM cars";

    private JdbcTemplate jdbcTemplate;

    public int save(Car car) {
        return jdbcTemplate.update(ADD_NEW_CAR, car.getBrand(), car.getModel(), car.getCostPerOneDay());
    }

    public int update(Car car) {
        return jdbcTemplate.update(UPDATE_CAR, car.getBrand(), car.getModel(), car.getCostPerOneDay(), car.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update(DELETE_CAR, id);
    }

    public Car getCarById(int id) {
        return jdbcTemplate.queryForObject(SELECT_CAR_BY_ID, new Object[]{id}, new CarRowMapper());
    }

    public List<Car> getCars() {
        return jdbcTemplate.query(SELECT_ALL_CARS, new CarRowMapper());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}