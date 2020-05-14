package dao.daoImpl;

import beans.Car;
import dao.CarDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.ConcertCarRowMapper;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String ADD_NEW_CAR = "INSERT INTO cars(brand,model,fuelTank) VALUES(?, ?, ?)";
    private static final String UPDATE_CAR = "UPDATE cars SET brand=?, model=?, fueltank=? WHERE id=?";
    private static final String DELETE = "DELETE FROM cars WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM cars WHERE id=?";
    private static final String SELECT_ALL_CARS = "SELECT * from cars";


    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Car car) {
        return template.update(ADD_NEW_CAR, car.getBrand(), car.getModel(), car.getFuelTank());
    }

    public int update(Car car) {
        return template.update(UPDATE_CAR, car.getBrand(), car.getModel(), car.getFuelTank(), car.getId());
    }

    public int delete(int id) {
        return template.update(DELETE, id);
    }

    public Car getCarById(int id) {
        return template.queryForObject(SELECT_BY_ID, new Object[]{id}, new ConcertCarRowMapper<Car>());
    }

    public List<Car> getCars() {

      return template.query(SELECT_ALL_CARS, new ConcertCarRowMapper<Car>());
    }
}