DROP DATABASE student_management_db;
CREATE DATABASE student_management_db;
USE student_management_db;
CREATE TABLE student
(
    Student_id    INT PRIMARY KEY AUTO_INCREMENT,
    Full_Name     VARCHAR(100) NOT NULL,
    Email         VARCHAR(100) NOT NULL UNIQUE,
    Phone_Number  VARCHAR(15)  NOT NULL,
    Register_Date DATE         NOT NULL,
    Status        BIT DEFAULT 1
);

DELIMITER //
CREATE PROCEDURE all_students_list()
BEGIN
SELECT * FROM student;
END //

CREATE PROCEDURE add_student(
    IN s_name VARCHAR(100),
    IN s_email VARCHAR(100),
    IN s_phone VARCHAR(15),
    IN s_date Date,
    IN s_status BIT
)
BEGIN
INSERT INTO student(full_name, email, phone_number, register_date, status)
VALUES (s_name, s_email, s_phone, s_date, s_status);
END //

CREATE PROCEDURE get_student_by_id(IN s_id INT)
BEGIN
SELECT *
FROM student
WHERE student_id = s_id;
END //


CREATE PROCEDURE update_student(
    IN s_id INT,
    IN s_name VARCHAR(100),
    IN s_email VARCHAR(100),
    IN s_phone VARCHAR(15),
    IN s_date Date,
    IN s_status BIT
)
BEGIN
UPDATE student
SET full_name     = s_name,
    email         = s_email,
    phone_number  = s_phone,
    register_date = s_date,
    status        = s_status
WHERE student_id = s_id;
END //

CREATE PROCEDURE delete_student(IN s_id INT)
BEGIN
DELETE FROM student WHERE student_id = s_id;
END //

CREATE PROCEDURE search_student(IN s_name VARCHAR(100))
BEGIN
SELECT * FROM student WHERE s_name LIKE CONCAT('%', s_name, '%');
END //

CREATE PROCEDURE sort_students()
BEGIN
SELECT * FROM student ORDER BY register_date ASC ;
END //
DELIMITER ;