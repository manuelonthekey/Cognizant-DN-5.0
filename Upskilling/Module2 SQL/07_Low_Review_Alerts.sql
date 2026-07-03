-- 7. Low Review Alerts
SELECT s.full_name, r.comments, c.title
FROM Reviews r
JOIN Students s ON r.student_id = s.student_id
JOIN Courses c ON r.course_id = c.course_id
WHERE r.rating < 3;