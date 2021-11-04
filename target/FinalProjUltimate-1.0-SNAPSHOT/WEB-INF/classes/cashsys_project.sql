DROP DATABASE IF EXISTS cashsysdb;

CREATE SCHEMA cashsysdb;

USE cashsysdb;

CREATE TABLE unit (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE product (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL,
    `price` DECIMAL(9,2) NOT NULL,
    `amount` DECIMAL(9,3) NOT NULL,
    `unit_id` INT NOT NULL,
    `barcode` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_unit1_idx` (`unit_id` ASC) VISIBLE,
    UNIQUE INDEX `barcode_UNIQUE` (`barcode` ASC) VISIBLE,
    CONSTRAINT `fk_product_unit1`
        FOREIGN KEY (`unit_id`)
            REFERENCES `cashsysdb`.`unit` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE user_role (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `middle_name` VARCHAR(45) NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `passhash` VARCHAR(355) NOT NULL,
    `user_role_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_user_role1_idx` (`user_role_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_user_role1`
        FOREIGN KEY (`user_role_id`)
            REFERENCES `cashsysdb`.`user_role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE payment (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE receipt (
    `id` INT NOT NULL,
    `payment_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_receipt_payment1_idx` (`payment_id` ASC) VISIBLE,
    INDEX `fk_receipt_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_receipt_payment1`
        FOREIGN KEY (`payment_id`)
            REFERENCES `cashsysdb`.`payment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_receipt_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `cashsysdb`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE receipt_has_product (
    `receipt_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `price` DECIMAL(9,2) NOT NULL,
    `amount` DECIMAL(9,3) NOT NULL,
    PRIMARY KEY (`receipt_id`, `product_id`),
    INDEX `fk_receipt_has_product_product1_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_receipt_has_product_receipt_idx` (`receipt_id` ASC) VISIBLE,
    CONSTRAINT `fk_receipt_has_product_receipt`
        FOREIGN KEY (`receipt_id`)
            REFERENCES `cashsysdb`.`receipt` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_receipt_has_product_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `cashsysdb`.`product` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);