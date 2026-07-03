-- 8. Lessons per Upcoming Course
SELECT c.title, COUNT(l.lesson_id) AS total_lessons
FROM Courses c
LEFT JOIN Lessons l ON c.course_id = l.course_id
WHERE c.status = 'upcoming'
GROUP BY c.course_id, c.title;