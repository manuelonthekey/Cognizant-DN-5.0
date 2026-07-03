-- Reference MySQL schema (equivalent to src/main/resources/schema.sql, which
-- targets H2). Use this if you switch the project to run against MySQL as
-- described in application.properties.

CREATE TABLE IF NOT EXISTS `ormlearn`.`country` (
  `co_id` INT NOT NULL AUTO_INCREMENT,
  `co_code` VARCHAR(10),
  `co_name` VARCHAR(100),
  PRIMARY KEY (`co_id`)
);

CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
  `st_id` INT NOT NULL AUTO_INCREMENT,
  `st_code` varchar(10),
  `st_date` date,
  `st_open` numeric(10,2),
  `st_close` numeric(10,2),
  `st_volume` numeric,
  PRIMARY KEY (`st_id`)
);

CREATE TABLE IF NOT EXISTS `ormlearn`.`department` (
  `dp_id` INT NOT NULL AUTO_INCREMENT,
  `dp_name` VARCHAR(100),
  PRIMARY KEY (`dp_id`)
);

CREATE TABLE IF NOT EXISTS `ormlearn`.`skill` (
  `sk_id` INT NOT NULL AUTO_INCREMENT,
  `sk_name` VARCHAR(100),
  PRIMARY KEY (`sk_id`)
);

CREATE TABLE IF NOT EXISTS `ormlearn`.`employee` (
  `em_id` INT NOT NULL AUTO_INCREMENT,
  `em_name` VARCHAR(100),
  `em_salary` DOUBLE,
  `em_permanent` BOOLEAN,
  `em_date_of_birth` DATE,
  `em_dp_id` INT,
  PRIMARY KEY (`em_id`),
  FOREIGN KEY (`em_dp_id`) REFERENCES `department`(`dp_id`)
);

CREATE TABLE IF NOT EXISTS `ormlearn`.`employee_skill` (
  `es_em_id` INT,
  `es_sk_id` INT,
  PRIMARY KEY (`es_em_id`, `es_sk_id`),
  FOREIGN KEY (`es_em_id`) REFERENCES `employee`(`em_id`),
  FOREIGN KEY (`es_sk_id`) REFERENCES `skill`(`sk_id`)
);
