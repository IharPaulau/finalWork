package service.impl;

import beans.Car;
import service.CarService;

import java.util.List;
import dao.CarDao;


public class CarServiceImpl implements CarService {

    private CarDao carDao;

    public int save(Car car) {
        return this.carDao.save(car);
    }

    public List<Car> getCars() {
        return carDao.getCars();
    }

    public Car getCarById(int id) {
        return carDao.getCarById(id);
    }

    public int update(Car car) {
        return this.carDao.update(car);
    }

    public int delete(int id) {
        return carDao.delete(id);
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }




}
