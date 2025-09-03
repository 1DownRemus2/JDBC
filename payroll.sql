CREATE DATABASE payroll_db;

USE payroll_db;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    base_salary DOUBLE,
    bonus DOUBLE,
    deductions DOUBLE
);

