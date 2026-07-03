-- 1. Student Active Courses
SELECT s.full_name, c.title, c.category, e.enroll_date
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
JOIN Courses c ON e.course_id = c.course_id
WHERE c.status = 'active'
ORDER BY e.enroll_date;