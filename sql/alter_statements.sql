-- payments table
ALTER TABLE `aurora`.`payment`
ADD COLUMN `wh_tax` DECIMAL(10,2) NOT NULL AFTER `amount`;


-- orders table
ALTER TABLE `aurora`.`orders`
DROP COLUMN `round_buy_count`,
DROP COLUMN `slim_buy_count`;

ALTER TABLE `aurora`.`orders`
ADD COLUMN `slim_buy_count` INT(11) NOT NULL DEFAULT '0' AFTER `cont_round_count`,
ADD COLUMN `round_buy_count` INT(11) NOT NULL DEFAULT '0' AFTER `slim_buy_count`;
