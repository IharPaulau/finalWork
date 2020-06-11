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
        LOGGER.info(String.format("Creating new car with id: %s", car.getId()));
        return carDao.save(car);
    }

    public List<Car> getCars()  {
        return carDao.getCars();
    }

    public Car getCarById(int carId) {
        try {
            return carDao.getCarById(carId);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.warn(String.format("Could not find car with id %s", carId));
            return null;
        }
    }

    public void update(Car car) {
        LOGGER.info(String.format("Updating car with id: %s", car.getId()));
        carDao.update(car);
    }


    public int delete(int carId) {
        LOGGER.info(String.format("Deleting car with id: %s", carId));
        return carDao.delete(carId);
    }

    @Override
    public void setCarNoMoreAvailable(Car car) {
        LOGGER.info(String.format("Setting car no more available with id: %s", car.getId()));
        carDao.updateCarAvailability(car.getId(), false);
    }

    @Override
    public void setCarAvailable(Car car) {
        LOGGER.info(String.format("Setting car now available with id: %s", car.getId()));
        carDao.updateCarAvailability(car.getId(), true);
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
}
