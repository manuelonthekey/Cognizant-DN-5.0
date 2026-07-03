-- 17. Multi_Category Instructors
SELECT s.full_name
FROM Students s
JOIN Courses c ON s.student_id = c.instructor_id
GROUP BY s.student_id, s.full_name
HAVING COUNT(DISTINCT c.category) > 1;