DROP DATABASE IF EXISTS cashsysdb;

CREATE DATABASE IF NOT EXISTS cashsysdb;

USE cashsysdb;

DROP TABLE IF EXISTS payment ;
DROP TABLE IF EXISTS unit  ;
DROP TABLE IF EXISTS product ;
DROP TABLE IF EXISTS user_role ;
DROP TABLE IF EXISTS `user` ;
DROP TABLE IF EXISTS receipt_status ;
DROP TABLE IF EXISTS receipt ;
DROP TABLE IF EXISTS receipt_has_product ;
DROP TABLE IF EXISTS `system` ;
DROP TABLE IF EXISTS user_details ;
DROP TABLE IF EXISTS product_details ;

CREATE TABLE IF NOT EXISTS payment (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS unit (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS product (
id INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(256) NOT NULL,
price DECIMAL(9,2) NOT NULL,
amount DECIMAL(9,3) NOT NULL,
barcode VARCHAR(128) NOT NULL,
unit_id INT NOT NULL,
PRIMARY KEY (id),
UNIQUE INDEX barcode_UNIQUE (barcode) ,
INDEX fk_product_unit1_idx (unit_id) ,
UNIQUE INDEX name_UNIQUE (`name`) ,
FULLTEXT INDEX IX_barcode_fulltext (barcode) ,
FULLTEXT INDEX IX_product_name_fulltext (`name`) ,
CONSTRAINT fk_product_unit1
   FOREIGN KEY (unit_id)
       REFERENCES unit (id)
       ON DELETE CASCADE
       ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS user_role (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS `user` (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
middle_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
passhash VARCHAR(355) NOT NULL,
role_id INT NOT NULL,
PRIMARY KEY (id),
INDEX fk_user_user_role1_idx (role_id) ,
CONSTRAINT fk_user_user_role1
  FOREIGN KEY (role_id)
      REFERENCES user_role (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS receipt_status (
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45) NOT NULL UNIQUE KEY);

CREATE TABLE IF NOT EXISTS receipt (
id INT NOT NULL AUTO_INCREMENT,
date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`change` DECIMAL(9,2) NOT NULL,
payment_id INT NOT NULL,
user_id INT NOT NULL,
status_id INT NOT NULL,
PRIMARY KEY (id),
INDEX fk_receipt_payment1_idx (payment_id) ,
INDEX fk_receipt_user1_idx (user_id) ,
INDEX fk_receipt_receipt_status1_idx (status_id) ,
CONSTRAINT fk_receipt_payment1
   FOREIGN KEY (payment_id)
       REFERENCES payment (id)
       ON UPDATE CASCADE,
CONSTRAINT fk_receipt_user1
   FOREIGN KEY (user_id)
       REFERENCES user (id)
       ON DELETE CASCADE
       ON UPDATE CASCADE,
CONSTRAINT fk_receipt_receipt_status1
   FOREIGN KEY (status_id)
       REFERENCES receipt_status (id)
       ON DELETE RESTRICT
       ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS receipt_has_product (
receipt_id INT NOT NULL,
product_id INT NOT NULL,
price DECIMAL(9,2) NOT NULL,
amount DECIMAL(9,3) NOT NULL,
PRIMARY KEY (receipt_id, product_id),
INDEX fk_receipt_has_product_product1_idx (product_id) ,
INDEX fk_receipt_has_product_receipt_idx (receipt_id) ,
CONSTRAINT fk_receipt_has_product_product1
   FOREIGN KEY (product_id)
       REFERENCES product (id)
       ON DELETE CASCADE
       ON UPDATE CASCADE,
CONSTRAINT fk_receipt_has_product_receipt
   FOREIGN KEY (receipt_id)
       REFERENCES receipt (id)
       ON DELETE CASCADE
       ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `system` (
organization_tax_id_number INT PRIMARY KEY,
name_organization VARCHAR(256) NOT NULL,
address_trade_point VARCHAR(512) NOT NULL,
vat DECIMAL(9,2) NOT NULL,
taxation_sys VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS user_details (
user_id INT PRIMARY KEY,
CONSTRAINT fk_user_details_user1
    FOREIGN KEY (user_id)
        REFERENCES `user` (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS product_details (
product_id INT PRIMARY KEY,
CONSTRAINT fk_product_details_product1
   FOREIGN KEY (product_id)
       REFERENCES product (id)
       ON DELETE NO ACTION
       ON UPDATE NO ACTION);