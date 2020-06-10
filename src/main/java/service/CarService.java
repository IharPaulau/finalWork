package service;

import models.Car;

import java.util.List;

public interface CarService {

    /**
     * Saved current car
     * @param car
     * @return carId
     */
    int save(Car car);

    /**
     * Get list of all cars
     *
     * @return list of all cars
     */
    List<Car> getCars();

    /**
     * Get car by current carId
     *
     * @param carId carId to search
     * @return Car
     */
    Car getCarById(int carId);

    /**
     * Update current car
     *
     * @param car car to update
     */
    void update(Car car);

    /**
     * @param carId
     * @return
     */
    int delete(int carId);

    void setCarNoMoreAvailable(Car car);

    void setCarAvailable(Car car);
}
