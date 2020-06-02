package service;

import beans.Car;

import java.util.List;

public interface CarService {

    int save(Car car);

    List<Car> getCars();

    Car getCarById(int id);

    void update(Car car);

    int delete(int id);

    void setCarNoMoreAvailable(Car car);

    void setCarAvailable(Car car);
}
