-- 12. Course with Maximum Lessons
SELECT c.title, COUNT(l.lesson_id) AS total_lessons
FROM Courses c
JOIN Lessons l ON c.course_id = l.course_id
GROUP BY c.course_id, c.title
ORDER BY total_lessons DESC
LIMIT 1;