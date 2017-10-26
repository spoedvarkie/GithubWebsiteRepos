-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(45) NOT NULL,
  `LAST_NAME` VARCHAR(45) NOT NULL,
  `CONTACT_NUM` VARCHAR(10) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TEXTBOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TEXTBOOK` (
  `TEXTBOOK_ID` INT NOT NULL AUTO_INCREMENT,
  `TEXTBOOK_TITLE` VARCHAR(45) NOT NULL,
  `TEXTBOOK_EDITION` VARCHAR(45) NOT NULL,
  `TEXTBOOK_ISBN` VARCHAR(45) NOT NULL,
  `TEXTBOOK_PRICE` DECIMAL(9,2) NOT NULL,
  `TEXTBOOK_COMMENTS` MEDIUMTEXT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`TEXTBOOK_ID`),
  INDEX `fk_TEXTBOOK_USER_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_TEXTBOOK_USER`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `mydb`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AUTHOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AUTHOR` (
  `AUTHOR_ID` INT NOT NULL AUTO_INCREMENT,
  `AUTHOR_INITIALS` VARCHAR(45) NOT NULL,
  `AUTHOR_L_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TEXTBOOK_AUTHOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TEXTBOOK_AUTHOR` (
  `TEXTBOOK_AUTHOR_ID` INT NOT NULL AUTO_INCREMENT,
  `TEXTBOOK_ID` INT NOT NULL,
  `AUTHOR_ID` INT NOT NULL,
  PRIMARY KEY (`TEXTBOOK_AUTHOR_ID`, `TEXTBOOK_ID`, `AUTHOR_ID`),
  INDEX `fk_TEXTBOOK_AUTHOR_TEXTBOOK1_idx` (`TEXTBOOK_ID` ASC),
  INDEX `fk_TEXTBOOK_AUTHOR_AUTHOR1_idx` (`AUTHOR_ID` ASC),
  CONSTRAINT `fk_TEXTBOOK_AUTHOR_TEXTBOOK1`
    FOREIGN KEY (`TEXTBOOK_ID`)
    REFERENCES `mydb`.`TEXTBOOK` (`TEXTBOOK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TEXTBOOK_AUTHOR_AUTHOR1`
    FOREIGN KEY (`AUTHOR_ID`)
    REFERENCES `mydb`.`AUTHOR` (`AUTHOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`IMAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`IMAGE` (
  `IMAGE_ID` INT NOT NULL AUTO_INCREMENT,
  `IMAGE_FILE` LONGBLOB NULL,
  `TEXTBOOK_ID` INT NOT NULL,
  PRIMARY KEY (`IMAGE_ID`),
  INDEX `fk_IMAGE_TEXTBOOK1_idx` (`TEXTBOOK_ID` ASC),
  CONSTRAINT `fk_IMAGE_TEXTBOOK1`
    FOREIGN KEY (`TEXTBOOK_ID`)
    REFERENCES `mydb`.`TEXTBOOK` (`TEXTBOOK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
