-- 4. Heavy Lesson Load
SELECT c.title, COUNT(l.lesson_id) AS heavy_lessons
FROM Courses c
JOIN Lessons l ON c.course_id = l.course_id
WHERE l.duration_mins > 60
GROUP BY c.course_id, c.title;