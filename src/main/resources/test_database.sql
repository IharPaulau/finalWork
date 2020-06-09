CREATE DATABASE IF NOT EXISTS test_db;
USE test_db;

-- Table to store Users
CREATE TABLE users (
    id          INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(45)    NOT NULL UNIQUE,
    password    VARCHAR(255)   NOT NULL,
    email       VARCHAR(45)    NOT NULL UNIQUE
) ENGINE = InnoDB;

-- Table to store Roles
CREATE TABLE roles (
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(20) NOT NULL UNIQUE
) ENGINE = InnoDB;

-- Table for mapping Users and Roles
CREATE TABLE users_to_roles (
    userId INT NOT NULL,
    roleId INT NOT NULL,

    FOREIGN KEY (userId) REFERENCES users (id),
    FOREIGN KEY (roleId) REFERENCES roles (id),

    UNIQUE (userId, roleId)
) ENGINE = InnoDB;

-- Table to store Cars
CREATE TABLE cars (
    id              INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand           VARCHAR(45) DEFAULT NULL,
    model           VARCHAR(45) DEFAULT NULL,
    typeBody        VARCHAR(45) DEFAULT NULL,
    typeEngine      VARCHAR(45) DEFAULT NULL,
    bodyColor       VARCHAR(45) DEFAULT NULL,
    costPerOneDay   INT         DEFAULT NULL,
    transmission    VARCHAR(45) DEFAULT NULL,
    available       TINYINT     DEFAULT true
) ENGINE = InnoDB;

-- Table of orders
CREATE TABLE orders (
    id                  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId              INT NULL,
    carId               INT NULL,
    passportSeries      VARCHAR(45) NULL,
    passportNumber      VARCHAR(45) NULL,
    passportId          VARCHAR(45) NULL,
    orderStatus    	    ENUM('REJECTED', 'APPROVED', 'NOT_VERIFIED', 'IN_RENT', 'RETURN', 'RECOVERY', 'COMPLETED')  NULL,
    payTillDate         VARCHAR(45) NULL,
    rentalEndTime       VARCHAR(45) NULL,
    rentalStartTime     VARCHAR(45) NULL,
    rentalPeriodInDays  INT NULL,
    compensationAmount  INT NULL
 ) ENGINE = InnoDB;

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');