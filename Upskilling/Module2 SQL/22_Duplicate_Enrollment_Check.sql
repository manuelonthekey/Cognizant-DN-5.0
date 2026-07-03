-- 22. Duplicate Enrollment Check
SELECT student_id, course_id, COUNT(*) AS enrollments
FROM Enrollments
GROUP BY student_id, course_id
HAVING COUNT(*) > 1;