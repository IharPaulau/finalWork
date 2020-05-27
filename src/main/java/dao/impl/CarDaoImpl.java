package dao.impl;

import beans.Car;
import dao.CarDao;

import org.springframework.jdbc.core.JdbcTemplate;

import mappers.CarRowMapper;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String ADD_NEW_CAR = "INSERT INTO cars(brand,model,typeBody,typeEngine,bodyColor,costPerOneDay,transmission) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_CAR = "UPDATE cars SET brand=?,model=?,typeBody=?,typeEngine=?,bodyColor=?,costPerOneDay=?,transmission=? WHERE id=?";
    private static final String DELETE_CAR = "DELETE FROM cars WHERE id=?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
    private static final String SELECT_ALL_CARS = "SELECT * FROM cars";
    private static final String NO_MORE_AVAILABLE = "UPDATE cars SET available = 0 WHERE id =?";
    private static final String NOW_AVAILABLE = "UPDATE cars SET available = 1 WHERE id =?";

    private JdbcTemplate jdbcTemplate;

    public int save(Car car) {
        return jdbcTemplate.update(ADD_NEW_CAR, car.getBrand(), car.getModel(), car.getTypeBody(), car.getTypeEngine(), car.getBodyColor(), car.getCostPerOneDay(), car.getTransmission());
    }

    public int update(Car car) {
        return jdbcTemplate.update(UPDATE_CAR, car.getBrand(), car.getModel(), car.getTypeBody(), car.getTypeEngine(), car.getBodyColor(), car.getCostPerOneDay(), car.getTransmission(), car.getId());
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

    @Override
    public int setCarNoMoreAvailable(Car car) {
        return jdbcTemplate.update(NO_MORE_AVAILABLE, car.getId());
    }

    @Override
    public int setCarAvailable(Car car) {
        return jdbcTemplate.update(NOW_AVAILABLE, car.getId());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}