-- 18. Material Availability Check
SELECT c.title
FROM Courses c
LEFT JOIN Materials m ON c.course_id = m.course_id
WHERE m.material_id IS NULL;