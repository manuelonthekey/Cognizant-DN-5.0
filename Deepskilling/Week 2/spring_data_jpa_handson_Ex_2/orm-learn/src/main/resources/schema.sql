-- ===================== Query Methods demo tables =====================

DROP TABLE IF EXISTS country;
CREATE TABLE country (
  co_id   INT AUTO_INCREMENT PRIMARY KEY,
  co_code VARCHAR(10),
  co_name VARCHAR(100)
);

DROP TABLE IF EXISTS stock;
CREATE TABLE stock (
  st_id     INT AUTO_INCREMENT PRIMARY KEY,
  st_code   VARCHAR(10),
  st_date   DATE,
  st_open   NUMERIC(10,2),
  st_close  NUMERIC(10,2),
  st_volume NUMERIC(15)
);

-- ===================== O/R Mapping demo tables =====================

DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS skill;

CREATE TABLE department (
  dp_id   INT AUTO_INCREMENT PRIMARY KEY,
  dp_name VARCHAR(100)
);

CREATE TABLE skill (
  sk_id   INT AUTO_INCREMENT PRIMARY KEY,
  sk_name VARCHAR(100)
);

CREATE TABLE employee (
  em_id            INT AUTO_INCREMENT PRIMARY KEY,
  em_name          VARCHAR(100),
  em_salary        DOUBLE,
  em_permanent     BOOLEAN,
  em_date_of_birth DATE,
  em_dp_id         INT,
  CONSTRAINT fk_employee_department FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

-- Join table for the ManyToMany relationship between Employee and Skill
CREATE TABLE employee_skill (
  es_em_id INT,
  es_sk_id INT,
  PRIMARY KEY (es_em_id, es_sk_id),
  CONSTRAINT fk_es_employee FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
  CONSTRAINT fk_es_skill FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);
