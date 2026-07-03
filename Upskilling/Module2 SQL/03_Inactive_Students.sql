-- 3. Inactive Students
SELECT s.full_name, s.email
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id AND e.enroll_date >= CURDATE() - INTERVAL 90 DAY
WHERE e.enrollment_id IS NULL;