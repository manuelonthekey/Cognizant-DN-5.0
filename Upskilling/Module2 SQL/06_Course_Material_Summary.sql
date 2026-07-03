-- 6. Course Material Summary
SELECT c.title, 
       SUM(CASE WHEN m.material_type = 'pdf' THEN 1 ELSE 0 END) AS pdf_count,
       SUM(CASE WHEN m.material_type = 'video' THEN 1 ELSE 0 END) AS video_count,
       SUM(CASE WHEN m.material_type = 'code' THEN 1 ELSE 0 END) AS code_count
FROM Courses c
LEFT JOIN Materials m ON c.course_id = m.course_id
GROUP BY c.course_id, c.title;