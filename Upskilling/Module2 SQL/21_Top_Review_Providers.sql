-- 21. Top Review Providers
SELECT s.full_name, COUNT(r.review_id) AS total_reviews
FROM Students s
JOIN Reviews r ON s.student_id = r.student_id
GROUP BY s.student_id, s.full_name
ORDER BY total_reviews DESC
LIMIT 5;