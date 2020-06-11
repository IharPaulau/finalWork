package dao;

import models.Car;

import java.util.List;

public interface CarDao {

    /**
     * Save current car
     *
     * @param car car to save
     * @return Car Id
     */
    int save(Car car);

    /**
     * Update attributes in current car
     *
     * @param car car to changes attributes
     * @return
     */
    int update(Car car);

    /**
     * Delete car by current carId
     *
     * @param carId carId to search car
     * @return
     */
    int delete(int carId);

    /**
     * Search car by current carId
     *
     * @param carId carId to search car
     * @return Car
     */
    Car getCarById(int carId);

    /**
     * Get list of all cars
     *
     * @return list of cars
     */
    List<Car> getCars();

    /**
     * Update available status of current car
     *
     * @param carId to search car
     * @param available new value of attributes
     * @return
     */
    int updateCarAvailability(int carId, boolean available);


}
