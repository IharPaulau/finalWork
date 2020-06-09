package service;

import models.Car;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractServiceTest {

    protected static final String TEST_USERNAME = "TestUsername";
    protected static final String TEST_USERNAME_2 = "TestUsername2";
    protected static final String TEST_NOT_EXISTING_USERNAME = "TestNotExistingUsername";
    protected static final String TEST_USER_EMAIL = "test@gmail.com";
    protected static final String TEST_USER_EMAIL_2 = "test2@gmail.com";
    protected static final String TEST_USER_PASSWORD = "password";

    protected static final String TEST_CAR_BRAND = "Test Porsche";
    protected static final String TEST_CAR_MODEL = "911";
    protected static final String TEST_CAR_TYPE_BODY = "Coupe";
    protected static final String TEST_CAR_TYPE_ENGINE = "Gasoline";
    protected static final String TEST_CAR_BODY_COLOR = "Black";
    protected static final String TEST_CAR_TRANSMISSION = "Automate";
    protected static final Integer TEST_CAR_COST = 300;

    @Autowired
    protected UserService userService;
    @Autowired
    protected CarService carService;

    protected User saveTestUser() {
        return userService.saveUserWithRole(createTestUser(TEST_USERNAME, TEST_USER_EMAIL));
    }

    protected int saveTestCar() {
        return carService.save(createTestCar(TEST_CAR_BRAND));
    }

    protected User createTestUser(String testUsername, String testUserEmail) {
        User testUser = new User();
        testUser.setUsername(testUsername);
        testUser.setPassword(TEST_USER_PASSWORD);
        testUser.setEmail(testUserEmail);
        return testUser;
    }

    protected Car createTestCar(final String carBrand) {
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

