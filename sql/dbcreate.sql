-- MySQL Workbench Forward Engineering



SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `library`;
CREATE SCHEMA `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`role` ;

CREATE TABLE IF NOT EXISTS `library`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `library`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user` ;

CREATE TABLE IF NOT EXISTS `library`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role_id` INT NOT NULL DEFAULT 2,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `library`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;



-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`book` ;

CREATE TABLE IF NOT EXISTS `library`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `count` INT NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `library`.`user_has_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user_has_book` ;

CREATE TABLE IF NOT EXISTS `library`.`user_has_book` (
  `user_id` INT(11) NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `book_id`),
  INDEX `fk_user_has_book_book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_user_has_book_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_book_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE user_has_book ADD UNIQUE (user_id, book_id);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `library`.`role` (`id`, `name`) VALUES ('1', 'admin');
INSERT INTO `library`.`role` (`id`, `name`) VALUES ('2', 'client');

INSERT INTO `library`.`user` (`login`, `password`, `role_id`) VALUES ('admin', 'admin', '1');
INSERT INTO `library`.`user` (`login`, `password`) VALUES ('client', 'client');
INSERT INTO `library`.`user` (`login`, `password`) VALUES ('ivan', 'ivan');
INSERT INTO `library`.`user` (`login`, `password`) VALUES ('aladin', 'adladin');

INSERT INTO `library`.`book` (`name`, `count`) VALUES ('Kurochka Ryaba', '3');
INSERT INTO `library`.`book` (`name`, `count`) VALUES ('Kolobok', '4');