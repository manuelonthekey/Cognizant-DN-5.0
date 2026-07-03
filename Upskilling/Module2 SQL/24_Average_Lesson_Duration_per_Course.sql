-- 24. Average Lesson Duration per Course
SELECT c.title, AVG(l.duration_mins) AS avg_duration
FROM Courses c
JOIN Lessons l ON c.course_id = l.course_id
GROUP BY c.course_id, c.title;