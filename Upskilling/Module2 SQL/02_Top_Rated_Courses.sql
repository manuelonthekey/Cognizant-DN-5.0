-- 2. Top Rated Courses
SELECT c.title, AVG(r.rating) AS avg_rating
FROM Courses c
JOIN Reviews r ON c.course_id = r.course_id
GROUP BY c.course_id, c.title
HAVING COUNT(r.review_id) >= 10
ORDER BY avg_rating DESC;