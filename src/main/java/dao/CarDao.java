package dao;

import models.Car;

import java.util.List;

public interface CarDao {
    int save(Car car);

    int update(Car car);

    int delete(int id);

    Car getCarById(int id);

    List<Car> getCars();

    int updateCarAvailability(int carId, boolean available);


}
