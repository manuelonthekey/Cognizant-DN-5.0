-- 23. Enrollment Trends
SELECT DATE_FORMAT(enroll_date, '%Y-%m') AS month, COUNT(enrollment_id) AS num_enrollments
FROM Enrollments
WHERE enroll_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY month
ORDER BY month;