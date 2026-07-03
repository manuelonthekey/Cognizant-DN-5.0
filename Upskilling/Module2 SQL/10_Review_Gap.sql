-- 10. Review Gap
SELECT c.title
FROM Courses c
JOIN Enrollments e ON c.course_id = e.course_id
LEFT JOIN Reviews r ON c.course_id = r.course_id
WHERE r.review_id IS NULL
GROUP BY c.course_id, c.title;