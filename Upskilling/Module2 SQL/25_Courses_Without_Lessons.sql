-- 25. Courses Without Lessons
SELECT c.title
FROM Courses c
LEFT JOIN Lessons l ON c.course_id = l.course_id
WHERE l.lesson_id IS NULL;