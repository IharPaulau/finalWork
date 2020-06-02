package service;

import beans.Car;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/application-context.xml")
public class CarServiceTest {

    private static final String TEST_CAR_BRAND = "Test Porsche";
    private static final String TEST_CAR_BRAND_2 = "Test Porsche 2";
    private static final String TEST_CAR_MODEL = "911";
    private static final String TEST_CAR_TYPE_BODY = "Coupe";
    private static final String TEST_CAR_TYPE_ENGINE = "Gasoline";
    private static final String TEST_CAR_BODY_COLOR = "Black";
    private static final String TEST_CAR_TRANSMISSION = "Automate";
    private static final Integer TEST_CAR_COST = 300;

    private int testCarId;

    @Autowired
    private CarService carService;

    @Before
    public void init() {
        testCarId = carService.save(createTestCar(TEST_CAR_BRAND));
    }

    @Test
    public void test_saveCar() {
        Car notExistingCar = carService.getCarById(testCarId + 1);
        assertNull(notExistingCar);
        Car carToSave = createTestCar(TEST_CAR_BRAND_2);
        carService.save(carToSave);
        Car savedCar = carService.getCarById(testCarId + 1);
        assertNotNull(savedCar);
        assertEquals(TEST_CAR_BRAND_2, savedCar.getBrand());
    }

    @Test
    public void test_getCars() {
        List<Car> cars = carService.getCars();
        assertFalse(cars.isEmpty());
    }

    @Test
    public void test_getCarById() {
        Car car = carService.getCarById(testCarId);
        assertEquals(TEST_CAR_BRAND, car.getBrand());
        assertEquals(TEST_CAR_MODEL, car.getModel());
        assertEquals(TEST_CAR_TYPE_BODY, car.getTypeBody());
        assertEquals(TEST_CAR_TYPE_ENGINE, car.getTypeEngine());
        assertEquals(TEST_CAR_BODY_COLOR, car.getBodyColor());
        assertEquals(TEST_CAR_COST, car.getCostPerOneDay());
        assertEquals(TEST_CAR_TRANSMISSION, car.getTransmission());
        assertTrue(car.isAvailable());
    }

    @Test
    public void test_update() {
        Car car = carService.getCarById(testCarId);
        assertEquals(TEST_CAR_BRAND, car.getBrand());
        car.setBrand(TEST_CAR_BRAND_2);
        carService.update(car);

        Car updatedCar = carService.getCarById(testCarId);
        assertEquals(TEST_CAR_BRAND_2, updatedCar.getBrand());
    }

    @Test
    public void test_delete() {
        Car car = carService.getCarById(testCarId);
        assertNotNull(car);
        carService.delete(testCarId);
        Car deletedCar = carService.getCarById(testCarId);
        assertNull(deletedCar);
    }

    @Test
    public void test_updateCarAvailability() {
        Car car = carService.getCarById(testCarId);
        assertTrue(car.isAvailable());
        carService.setCarNoMoreAvailable(car);

        car = carService.getCarById(testCarId);
        assertFalse(car.isAvailable());
        carService.setCarAvailable(car);

        car = carService.getCarById(testCarId);
        assertTrue(car.isAvailable());
    }

    private Car createTestCar(final String carBrand) {
        Car testCar = new Car();
        testCar.setBrand(carBrand);
        testCar.setModel(TEST_CAR_MODEL);
        testCar.setTypeBody(TEST_CAR_TYPE_BODY);
        testCar.setTypeEngine(TEST_CAR_TYPE_ENGINE);
        testCar.setBodyColor(TEST_CAR_BODY_COLOR);
        testCar.setCostPerOneDay(TEST_CAR_COST);
        testCar.setTransmission(TEST_CAR_TRANSMISSION);
        return testCar;
    }
}

