-- 16. Unenrolled Active Students
SELECT s.full_name, s.email
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
WHERE s.signup_date >= CURDATE() - INTERVAL 30 DAY
AND e.enrollment_id IS NULL;