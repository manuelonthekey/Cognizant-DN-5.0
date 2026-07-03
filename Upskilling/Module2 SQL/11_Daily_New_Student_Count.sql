-- 11. Daily New Student Count
SELECT signup_date, COUNT(student_id) AS num_students
FROM Students
WHERE signup_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY signup_date;