CREATE SCHEMA `aurora` ;
USE aurora;

-- CUSTOMER
CREATE TABLE `aurora`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NULL,
  `contact_name` VARCHAR(45) NULL,
  `main_number` VARCHAR(45) NULL,
  `alternate_number` VARCHAR(45) NULL,
  `email_address` VARCHAR(45) NULL,
  `order_interval` INT NULL,
  PRIMARY KEY (`customer_id`))
COMMENT = 'Customer details';

-- ORDER
CREATE TABLE `aurora`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `delivery_receipt_num` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `amount_paid` DECIMAL(10,2) NOT NULL,
  `total_amount` DECIMAL(10,2) NOT NULL,
  `cont_slim_count` INT NOT NULL DEFAULT 0,
  `cont_round_count` INT NOT NULL DEFAULT 0,
  `cont_slim_returned` INT NOT NULL DEFAULT 0,
  `cont_round_returned` INT NOT NULL DEFAULT 0,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `remarks` VARCHAR(500) NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC));
  
-- DEBT
CREATE TABLE `aurora`.`debt` (
  `debt_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `remarks` VARCHAR(255) NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `linked_order_id` INT NULL,
  PRIMARY KEY (`debt_id`),
  UNIQUE INDEX `debt_id_UNIQUE` (`debt_id` ASC));

-- ACCOUNTS_RECEIVABLES replaces the DEBTS table above
CREATE TABLE `aurora`.`accounts_receivables` (
  `ar_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `remarks` VARCHAR(255) NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `linked_order_id` INT NULL,
  PRIMARY KEY (`ar_id`),
  UNIQUE INDEX `ar_id_UNIQUE` (`ar_id` ASC));

-- PAYMENT
CREATE TABLE `aurora`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `payment_type` VARCHAR(45) NOT NULL DEFAULT 'CASH',
  `remarks` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`payment_id`),
  UNIQUE INDEX `payment_id_UNIQUE` (`payment_id` ASC));

-- CONTAINER
CREATE TABLE `aurora`.`container` (
  `container_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `round_count` INT NULL,
  `slim_count` INT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`container_id`),
  UNIQUE INDEX `container_id_UNIQUE` (`container_id` ASC));

-- Expenses
CREATE TABLE `aurora`.`expense` (
  `expense_id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`expense_id`),
  UNIQUE INDEX `expense_id_UNIQUE` (`expense_id` ASC));

-- PRODUCTS
CREATE TABLE `aurora`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  `initial_price` DECIMAL(10,2) NOT NULL,
  `selling_price` DECIMAL(10,2) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `updated_at` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC));

-- CUSTOMER_PRICE
CREATE TABLE `aurora`.`customer_price` (
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `selling_price` DECIMAL(10,2) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `updated_at` DATETIME NOT NULL DEFAULT NOW());
