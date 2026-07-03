-- 19. Archived Courses with Review Summary
SELECT c.title, COUNT(e.enrollment_id) AS total_enrollments, AVG(r.rating) AS avg_rating
FROM Courses c
LEFT JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Reviews r ON c.course_id = r.course_id
WHERE c.status = 'archived'
GROUP BY c.course_id, c.title;