-- 14. Most Enrolled Courses
SELECT c.title, COUNT(e.enrollment_id) AS total_enrollments
FROM Courses c
JOIN Enrollments e ON c.course_id = e.course_id
GROUP BY c.course_id, c.title
ORDER BY total_enrollments DESC
LIMIT 3;