-- ==========================================
-- ORM-LEARN DATA POPULATION
-- ==========================================

-- Populate Country table
INSERT INTO country (co_code, co_name) VALUES ('IN', 'India');
INSERT INTO country (co_code, co_name) VALUES ('US', 'United States of America');
INSERT INTO country (co_code, co_name) VALUES ('RU', 'Russian Federation');
INSERT INTO country (co_code, co_name) VALUES ('ZM', 'Zambia');
INSERT INTO country (co_code, co_name) VALUES ('ZW', 'Zimbabwe');
INSERT INTO country (co_code, co_name) VALUES ('BV', 'Bouvet Island');
INSERT INTO country (co_code, co_name) VALUES ('DJ', 'Djibouti');
INSERT INTO country (co_code, co_name) VALUES ('GP', 'Guadeloupe');
INSERT INTO country (co_code, co_name) VALUES ('GS', 'South Georgia and the South Sandwich Islands');
INSERT INTO country (co_code, co_name) VALUES ('LU', 'Luxembourg');
INSERT INTO country (co_code, co_name) VALUES ('SS', 'South Sudan');
INSERT INTO country (co_code, co_name) VALUES ('TF', 'French Southern Territories');
INSERT INTO country (co_code, co_name) VALUES ('UM', 'United States Minor Outlying Islands');
INSERT INTO country (co_code, co_name) VALUES ('ZA', 'South Africa');

-- Populate Stock table (Facebook Sep 2019, Google > 1250, etc.)
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-03', 184.00, 182.39, 9779400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-04', 184.65, 187.14, 11308000);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-09-05', 188.53, 190.90, 13876700);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-22', 1236.67, 1253.76, 954200);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('GOOGL', '2019-04-29', 1280.51, 1296.20, 3618400);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2019-01-31', 165.60, 166.69, 77233600);
INSERT INTO stock (st_code, st_date, st_open, st_close, st_volume) VALUES ('FB', '2018-10-31', 155.00, 151.79, 60101300);

-- Populate Department table (ormlearn)
INSERT INTO department (dp_id, dp_name) VALUES (1, 'Human Resources');
INSERT INTO department (dp_id, dp_name) VALUES (2, 'Engineering');
INSERT INTO department (dp_id, dp_name) VALUES (3, 'Sales');

-- Populate Skill table (ormlearn)
INSERT INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT INTO skill (sk_id, sk_name) VALUES (2, 'Spring Boot');
INSERT INTO skill (sk_id, sk_name) VALUES (3, 'SQL');

-- Populate Employee table (ormlearn)
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES (1, 'John Doe', 75000.00, true, '1990-05-15', 2);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES (2, 'Jane Smith', 85000.00, true, '1992-08-20', 2);
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES (3, 'Charlie Brown', 50000.00, false, '1995-12-10', 1);

-- Link Employee and Skill
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 3);

-- Populate User table for quiz
INSERT INTO users (us_id, us_name) VALUES (1, 'john_doe');

-- Populate Question table
INSERT INTO question (qt_id, qt_text) VALUES (1, 'What is the extension of the hyper text markup language file?');
INSERT INTO question (qt_id, qt_text) VALUES (2, 'What is the maximum level of heading tag can be used in a HTML page?');

-- Populate Options table
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (1, 1, '.xhtm', 0.0, false);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (2, 1, '.ht', 0.0, false);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (3, 1, '.html', 1.0, true);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (4, 1, '.htmx', 0.0, false);

INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (5, 2, '5', 0.0, false);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (6, 2, '3', 0.0, false);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (7, 2, '4', 0.0, false);
INSERT INTO options (op_id, op_qt_id, op_text, op_score, op_is_correct) VALUES (8, 2, '6', 1.0, true);

-- Populate Attempt table
INSERT INTO attempt (at_id, at_us_id, at_date) VALUES (1, 1, CURRENT_TIMESTAMP());

-- Populate AttemptQuestion table
INSERT INTO attempt_question (aq_id, aq_at_id, aq_qt_id) VALUES (1, 1, 1);
INSERT INTO attempt_question (aq_id, aq_at_id, aq_qt_id) VALUES (2, 1, 2);

-- Populate AttemptOption table
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_op_id) VALUES (1, 1, 3); -- Selected '.html' for Q1
INSERT INTO attempt_option (ao_id, ao_aq_id, ao_op_id) VALUES (2, 2, 6); -- Selected '3' for Q2


-- ==========================================
-- EMPLOYEE MANAGEMENT SYSTEM (EMS) DATA POPULATION
-- ==========================================

-- Populate departments table (EMS)
INSERT INTO departments (id, name) VALUES (1, 'EMS Human Resources');
INSERT INTO departments (id, name) VALUES (2, 'EMS Engineering');
INSERT INTO departments (id, name) VALUES (3, 'EMS Sales');

-- Populate employees table (EMS)
INSERT INTO employees (id, name, email, department_id, created_by, created_date) VALUES (1, 'Alice Johnson', 'alice@ems.com', 2, 'SystemUser', CURRENT_TIMESTAMP());
INSERT INTO employees (id, name, email, department_id, created_by, created_date) VALUES (2, 'Bob Smith', 'bob@ems.com', 2, 'SystemUser', CURRENT_TIMESTAMP());
INSERT INTO employees (id, name, email, department_id, created_by, created_date) VALUES (3, 'Charlie Davis', 'charlie@ems.com', 1, 'SystemUser', CURRENT_TIMESTAMP());
INSERT INTO employees (id, name, email, department_id, created_by, created_date) VALUES (4, 'Diana Prince', 'diana@ems.com', 3, 'SystemUser', CURRENT_TIMESTAMP());
