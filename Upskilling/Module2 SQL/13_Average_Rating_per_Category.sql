-- 13. Average Rating per Category
SELECT c.category, AVG(r.rating) AS avg_rating
FROM Courses c
JOIN Reviews r ON c.course_id = r.course_id
GROUP BY c.category;