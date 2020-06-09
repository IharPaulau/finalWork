CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

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

INSERT INTO users VALUES (1, 'admin', '$2a$11$qpS3KqDvy9Xd1mq1xC/8jeycG1JCs2nmy7LWdefve/iAYXMsam9Ua', 'admin@gmail.com');
INSERT INTO users VALUES (2, 'pavlov', '$2a$11$MZvC3Xg76BwHqyhz2XJMGuan8i8/opQVdJ0zRnIUd8B4e5uilBsOi', 'pavlov@gmail.com');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_to_roles VALUES (1, 1);
INSERT INTO users_to_roles VALUES (2, 1);

INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (1, 'Porsche', '911', 'coupe', 'gasoline', 'black', 300, 'automate', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (2, 'Lada', 'XRAY Cross', 'sedan', 'gasoline', 'White', 75, 'manual', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (3, 'Mercedes', 'G 500', 'station wagon', 'gasoline', 'lack', 150, 'manual', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (4, 'BMW', '520d AT', 'sedan', 'diesel', 'black', 150, 'automate', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (5, 'FORD', 'TAURUS ', 'sedan', 'gasoline', 'brown', 100, 'automate', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (6, 'KIA', 'Cerato', 'sedan', 'gasoline', 'red', 100, 'automate', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (7, 'BMW', 'M550i xDrive AT', 'sedan', 'gasoline', 'black', 170, 'manual', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (8, 'Mercedes', 'AMG', 'coupe', 'gasoline', 'White', 300, 'automate', true);
INSERT INTO cars (id, brand, model, typeBody, typeEngine, bodyColor, costPerOneDay, transmission, available) VALUES (9, 'Tesla Motors', 'Model X', 'sedan', 'electric car', 'White', 100, 'manual', true);

