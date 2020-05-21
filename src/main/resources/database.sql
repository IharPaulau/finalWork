CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

-- Table to store Users
CREATE TABLE users (
    id          INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userName    VARCHAR(45)    NOT NULL UNIQUE,
    password    VARCHAR(255)    NOT NULL
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
    costPerOneDay   INT         DEFAULT NULL,
    fuelTank        INT         DEFAULT NULL
) ENGINE = InnoDB;

-- Table of orders

CREATE TABLE orders (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userId            INT NULL,
  carId             INT NULL,
  passportSeries    VARCHAR(45) NULL,
  passportNumber    VARCHAR(45) NULL,
  passportId        VARCHAR(45) NULL,
  orderApproved TINYINT NULL,
  rentalPeriodInDays INT NULL
   )ENGINE = InnoDB;

INSERT INTO users VALUES (1, 'admin', '$2a$11$qpS3KqDvy9Xd1mq1xC/8jeycG1JCs2nmy7LWdefve/iAYXMsam9Ua');
INSERT INTO users VALUES (2, 'pavlov', '$2a$11$MZvC3Xg76BwHqyhz2XJMGuan8i8/opQVdJ0zRnIUd8B4e5uilBsOi');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_to_roles VALUES (1, 1);
INSERT INTO users_to_roles VALUES (1, 2);
INSERT INTO users_to_roles VALUES (2, 1);
