-- 5. Most Active Countries
SELECT s.country, COUNT(DISTINCT e.student_id) AS num_students
FROM Students s
JOIN Enrollments e ON s.student_id = e.student_id
GROUP BY s.country
ORDER BY num_students DESC
LIMIT 5;