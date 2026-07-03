-- Creating tables
CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    country VARCHAR(100) NOT NULL,
    signup_date DATE NOT NULL
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('active', 'archived', 'upcoming'),
    instructor_id INT,
    FOREIGN KEY (instructor_id) REFERENCES Students(student_id)
);

CREATE TABLE Lessons (
    lesson_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    title VARCHAR(200) NOT NULL,
    duration_mins INT NOT NULL,
    publish_time DATETIME NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enroll_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    review_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Materials (
    material_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    material_type ENUM('pdf', 'video', 'code'),
    file_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

-- Inserting sample data
INSERT INTO Students (full_name, email, country, signup_date) VALUES
('Sarah Connor', 'sarah@example.com', 'USA', '2024-12-01'),
('John Smith', 'john@example.com', 'UK', '2024-12-05'),
('Priya Patel', 'priya@example.com', 'India', '2024-12-10'),
('Liam Neeson', 'liam@example.com', 'Ireland', '2025-01-15'),
('Emma Watson', 'emma@example.com', 'UK', '2025-02-01');

INSERT INTO Courses (title, description, category, start_date, end_date, status, instructor_id) VALUES
('Python 101', 'Intro to Python', 'IT', '2025-06-10 10:00:00', '2025-06-30 16:00:00', 'upcoming', 1),
('Advanced SQL', 'Master queries', 'Database', '2025-05-15 09:00:00', '2025-05-20 17:00:00', 'archived', 3),
('UX Design', 'Figma basics', 'Design', '2025-07-01 10:00:00', '2025-07-15 16:00:00', 'active', 2);

INSERT INTO Lessons (course_id, title, duration_mins, publish_time) VALUES
(1, 'Setup & Install', 45, '2025-06-10 10:00:00'),
(1, 'Variables & Types', 60, '2025-06-12 11:15:00'),
(2, 'Window Functions', 120, '2025-05-15 09:30:00'),
(3, 'Color Theory', 30, '2025-07-01 10:00:00');

INSERT INTO Enrollments (student_id, course_id, enroll_date) VALUES
(1, 1, '2025-05-01'),
(2, 1, '2025-05-02'),
(3, 2, '2025-04-30'),
(4, 2, '2025-04-28'),
(5, 3, '2025-06-15');

INSERT INTO Reviews (student_id, course_id, rating, comments, review_date) VALUES
(3, 2, 4, 'Great queries!', '2025-05-16'),
(4, 2, 5, 'Very detailed.', '2025-05-16'),
(2, 1, 3, 'Too basic.', '2025-06-11');

INSERT INTO Materials (course_id, material_type, file_url, uploaded_at) VALUES
(1, 'code', 'https://lms.com/repo/python101.zip', '2025-05-01 10:00:00'),
(2, 'pdf', 'https://lms.com/docs/sql_cheatsheet.pdf', '2025-04-20 09:00:00'),
(3, 'video', 'https://lms.com/vids/color_intro.mp4', '2025-06-25 15:00:00');
