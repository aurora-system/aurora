CREATE SCHEMA `aurora` ;
USE aurora;

-- This is a sample change

CREATE TABLE `aurora`.`order` (
  `order_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `delivery_receipt_num` VARCHAR(45) NOT NULL,
  `amount_paid` DECIMAL(2) NOT NULL,
  `total_amount` DECIMAL(2) NOT NULL,
  `cont_slim_count` INT NOT NULL DEFAULT 0,
  `cont_round_count` INT NOT NULL DEFAULT 0,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC));
  
CREATE TABLE `aurora`.`debt` (
  `debt_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `amount` DECIMAL(2) NOT NULL,
  `remarks` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`debt_id`))
  UNIQUE INDEX `debt_id_UNIQUE` (`debt_id` ASC);
  
CREATE TABLE `aurora`.`payment` (
  `payment_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `amount` DECIMAL(2) NOT NULL,
  `remarks` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`payment_id`),
  UNIQUE INDEX `payment_id_UNIQUE` (`payment_id` ASC));