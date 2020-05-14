package dao;

import beans.Car;

import java.util.List;

public interface CarDao {
    int save(Car car);

    int update(Car car);

    int delete(int id);

    Car getCarById(int id);

    List<Car> getCars();
}
