-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema project-ride
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project-ride
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project-ride` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `project-ride` ;

-- -----------------------------------------------------
-- Table `project-ride`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`user` (
  `Email` VARCHAR(40) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`employee` (
  `empId` VARCHAR(20) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `age` INT NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `address` VARCHAR(20) NOT NULL,
  `contact` VARCHAR(20) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`empId`, `username`),
  INDEX `username` (`username` ASC) VISIBLE,
  CONSTRAINT `employee_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `project-ride`.`user` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`attendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`attendance` (
  `attId` VARCHAR(20) NOT NULL,
  `month` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `status` VARCHAR(100) NOT NULL,
  `empId` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`attId`),
  INDEX `empId` (`empId` ASC) VISIBLE,
  CONSTRAINT `attendance_ibfk_1`
    FOREIGN KEY (`empId`)
    REFERENCES `project-ride`.`employee` (`empId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`passenger` (
  `passengerId` VARCHAR(20) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `age` INT NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `address` VARCHAR(20) NOT NULL,
  `contact` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`passengerId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`delivery` (
  `deliveryId` VARCHAR(20) NOT NULL,
  `passengerId` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`deliveryId`),
  INDEX `passengerId` (`passengerId` ASC) VISIBLE,
  CONSTRAINT `delivery_ibfk_1`
    FOREIGN KEY (`passengerId`)
    REFERENCES `project-ride`.`passenger` (`passengerId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`trains`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`trains` (
  `trainId` VARCHAR(20) NOT NULL,
  `type` VARCHAR(20) NULL DEFAULT NULL,
  `noOfSeats` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `ticketPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`trainId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`delivery_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`delivery_details` (
  `deliveryId` VARCHAR(20) NOT NULL,
  `trainId` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  `weight` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`deliveryId`),
  INDEX `trainId` (`trainId` ASC) VISIBLE,
  CONSTRAINT `delivery_details_ibfk_1`
    FOREIGN KEY (`deliveryId`)
    REFERENCES `project-ride`.`delivery` (`deliveryId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `delivery_details_ibfk_2`
    FOREIGN KEY (`trainId`)
    REFERENCES `project-ride`.`trains` (`trainId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`driver` (
  `driverId` VARCHAR(20) NOT NULL,
  `trainId` VARCHAR(20) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`driverId`),
  INDEX `trainId` (`trainId` ASC) VISIBLE,
  INDEX `username` (`username` ASC) VISIBLE,
  CONSTRAINT `driver_ibfk_1`
    FOREIGN KEY (`trainId`)
    REFERENCES `project-ride`.`trains` (`trainId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `driver_ibfk_2`
    FOREIGN KEY (`username`)
    REFERENCES `project-ride`.`user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`payroll`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`payroll` (
  `salaryId` VARCHAR(20) NOT NULL,
  `amount` VARCHAR(20) NOT NULL,
  `date` INT NOT NULL,
  `month` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `empId` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`salaryId`),
  INDEX `empId` (`empId` ASC) VISIBLE,
  CONSTRAINT `payroll_ibfk_1`
    FOREIGN KEY (`empId`)
    REFERENCES `project-ride`.`employee` (`empId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`reservation` (
  `reservationId` VARCHAR(20) NOT NULL,
  `passengerId` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`reservationId`),
  INDEX `passengerId` (`passengerId` ASC) VISIBLE,
  CONSTRAINT `reservation_ibfk_2`
    FOREIGN KEY (`passengerId`)
    REFERENCES `project-ride`.`passenger` (`passengerId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`reservation_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`reservation_detail` (
  `reservationId` VARCHAR(20) NOT NULL,
  `trainId` VARCHAR(20) NOT NULL,
  `noOfSeats` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`reservationId`),
  INDEX `trainId` (`trainId` ASC) VISIBLE,
  CONSTRAINT `reservation_detail_ibfk_1`
    FOREIGN KEY (`trainId`)
    REFERENCES `project-ride`.`trains` (`trainId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reservation_detail_ibfk_2`
    FOREIGN KEY (`reservationId`)
    REFERENCES `project-ride`.`reservation` (`reservationId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `project-ride`.`trainschedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project-ride`.`trainschedule` (
  `scheduleId` VARCHAR(20) NOT NULL,
  `trainId` VARCHAR(20) NOT NULL,
  `fromStation` VARCHAR(20) NOT NULL,
  `toStation` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `timeperiod` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`scheduleId`),
  INDEX `trainId` (`trainId` ASC) VISIBLE,
  CONSTRAINT `trainschedule_ibfk_1`
    FOREIGN KEY (`trainId`)
    REFERENCES `project-ride`.`trains` (`trainId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
