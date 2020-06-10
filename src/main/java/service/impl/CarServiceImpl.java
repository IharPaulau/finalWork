package service.impl;

import models.Car;
import service.CarService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import dao.CarDao;


public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = Logger.getLogger(CarServiceImpl.class);

    private CarDao carDao;

    public int save(Car car) {

        return carDao.save(car);
    }

    public List<Car> getCars()  {
        return carDao.getCars();
    }

    public Car getCarById(int id) {
        try {
            return carDao.getCarById(id);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.warn(String.format("Could not find car with id %s", id));
            return null;
        }
    }

    public void update(Car car) {
        carDao.update(car);
    }


    public int delete(int id) {
        return carDao.delete(id);
    }

    @Override
    public void setCarNoMoreAvailable(Car car) {
        carDao.updateCarAvailability(car.getId(), false);
    }

    @Override
    public void setCarAvailable(Car car) {
        carDao.updateCarAvailability(car.getId(), true);
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }


}
