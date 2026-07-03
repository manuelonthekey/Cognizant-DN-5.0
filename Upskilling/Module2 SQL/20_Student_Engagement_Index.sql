-- 20. Student Engagement Index
SELECT s.full_name, COUNT(DISTINCT e.enrollment_id) AS courses_enrolled, COUNT(DISTINCT r.review_id) AS reviews_submitted
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
LEFT JOIN Reviews r ON s.student_id = r.student_id
GROUP BY s.student_id, s.full_name;