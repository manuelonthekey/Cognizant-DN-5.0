-- ===================== Country sample data =====================
-- Includes the countries used in the 'ou' search example and the 'Z' index example
INSERT INTO country (co_code, co_name) VALUES
('BV', 'Bouvet Island'),
('DJ', 'Djibouti'),
('GP', 'Guadeloupe'),
('GS', 'South Georgia and the South Sandwich Islands'),
('LU', 'Luxembourg'),
('SS', 'South Sudan'),
('TF', 'French Southern Territories'),
('UM', 'United States Minor Outlying Islands'),
('ZA', 'South Africa'),
('ZM', 'Zambia'),
('ZW', 'Zimbabwe'),
('IN', 'India'),
('US', 'United States of America'),
('FR', 'France');

-- ===================== Stock sample data =====================
-- A representative subset of FB / GOOGL / NFLX data covering the query-method scenarios
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES
('FB', '2019-09-03', 184.00, 182.39, 9779400),
('FB', '2019-09-04', 184.65, 187.14, 11308000),
('FB', '2019-09-05', 188.53, 190.90, 13876700),
('FB', '2019-09-06', 190.21, 187.49, 15226800),
('FB', '2019-09-09', 187.73, 188.76, 14722400),
('FB', '2019-09-24', 187.98, 181.28, 18546600),
('FB', '2019-09-27', 180.49, 177.10, 14656200),
('FB', '2019-01-31', 165.60, 166.69, 77233600),
('FB', '2018-10-31', 155.00, 151.79, 60101300),
('FB', '2018-12-19', 141.21, 133.24, 57404900),
('GOOGL', '2019-04-22', 1236.67, 1253.76, 954200),
('GOOGL', '2019-04-23', 1256.64, 1270.59, 1593400),
('GOOGL', '2019-04-24', 1270.59, 1260.05, 1169800),
('GOOGL', '2019-04-25', 1270.30, 1267.34, 1567200),
('GOOGL', '2019-04-26', 1273.38, 1277.42, 1361400),
('GOOGL', '2019-04-29', 1280.51, 1296.20, 3618400),
('GOOGL', '2019-10-17', 1251.40, 1252.80, 1047900),
('NFLX', '2018-12-24', 242.00, 233.88, 9547600),
('NFLX', '2018-12-21', 263.83, 246.39, 21397600),
('NFLX', '2018-12-26', 233.92, 253.67, 14402700);

-- ===================== Payroll sample data (O/R mapping demo) =====================
INSERT INTO department (dp_name) VALUES
('Engineering'),
('Human Resources'),
('Finance');

INSERT INTO skill (sk_name) VALUES
('Java'),
('SQL'),
('Spring Boot'),
('Communication');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES
('Alice Johnson', 75000.00, TRUE,  '1990-04-12', 1),
('Bob Smith',     65000.00, TRUE,  '1988-11-02', 1),
('Carol White',   58000.00, FALSE, '1995-06-23', 2),
('David Brown',   82000.00, TRUE,  '1985-01-30', 3);

-- Many-to-Many: employee <-> skill
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES
(1, 1), -- Alice - Java
(1, 3), -- Alice - Spring Boot
(2, 1), -- Bob - Java
(2, 2), -- Bob - SQL
(3, 4), -- Carol - Communication
(4, 2); -- David - SQL
