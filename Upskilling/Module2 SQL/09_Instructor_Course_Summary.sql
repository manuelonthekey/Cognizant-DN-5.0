-- 9. Instructor Course Summary
SELECT s.full_name AS instructor_name,
       SUM(CASE WHEN c.status = 'active' THEN 1 ELSE 0 END) AS active_courses,
       SUM(CASE WHEN c.status = 'archived' THEN 1 ELSE 0 END) AS archived_courses,
       SUM(CASE WHEN c.status = 'upcoming' THEN 1 ELSE 0 END) AS upcoming_courses
FROM Students s
JOIN Courses c ON s.student_id = c.instructor_id
GROUP BY s.student_id, s.full_name;