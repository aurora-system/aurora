-- payments table
ALTER TABLE `aurora`.`payment`
ADD COLUMN `wh_tax` DECIMAL(10,2) NOT NULL AFTER `amount`;
